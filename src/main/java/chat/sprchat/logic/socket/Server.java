package chat.sprchat.logic.socket;

import chat.sprchat.SprchatApplication;
import chat.sprchat.state.ConnectedClient;
import chat.sprchat.state.InetSocket;
import chat.sprchat.state.LoadedMessage;
import chat.sprchat.state.orm.Message;
import chat.sprchat.state.orm.MsgRepo;
import com.google.gson.Gson;
import lombok.val;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

@Component
public class Server extends WebSocketServer
{
    public static MsgRepo msgRepo;

    public Server(InetSocket address, MsgRepo _msgRepo)
    {
        super(address);
        this.start();
        System.out.println(InetSocket.ip);

        msgRepo = _msgRepo;
        val msgs = msgRepo.findAll();
        for(var msg: msgs)
        {
            SprchatApplication.loadedMessages.add(new LoadedMessage(msg.getUser(),msg.getMessage(), msg.getDate()));
        }
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake)
    {
        val newClient = new ConnectedClient(webSocket, clientHandshake);
        SprchatApplication.clients.add(newClient);

        val gson = new Gson();
        val msgSend = gson.toJson(SprchatApplication.loadedMessages);
        webSocket.send("#newMsg"+msgSend);
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b)
    {
        val address = webSocket.getRemoteSocketAddress();
        SprchatApplication.clients.removeIf(c -> c.getSocket().getRemoteSocketAddress() == address);
    }

    @Override
    public void onMessage(WebSocket webSocket, String s)
    {
        switch(s)
        {
            case "#connectedUsers" -> webSocket.send("connectedUsers#" + SprchatApplication.clients.size());
            default -> {
                if(s.startsWith("#setName:"))
                {
                    val name = s.substring("#setName:".length());
                    for(var c: SprchatApplication.clients)
                        if(c.getSocket() == webSocket)
                            c.setName(name);
                }
                else if(s.startsWith("#msg:"))
                {
                    val msg = s.substring("#msg:".length());
                    val data = msg.split(":");
                    var username = "";
                    for(var c: SprchatApplication.clients)
                        if(c.getSocket() == webSocket)
                            username = c.getName();
                    LoadedMessage newMessage = null;
                    try
                    {
                        newMessage = new LoadedMessage(username, data[0], new SimpleDateFormat("E MMM dd yyyy HH").parse(data[1]));
                    }
                    catch(ParseException e)
                    {
                        throw new RuntimeException(e);
                    }
                    SprchatApplication.loadedMessages.add(newMessage);
                    msgRepo.save(new Message(newMessage.getUser(), newMessage.getDate(), newMessage.getMessage()));

                    val gson = new Gson();
                    val msgSend = gson.toJson(SprchatApplication.loadedMessages);
                    for(var c: SprchatApplication.clients)
                        c.getSocket().send("#newMsg"+msgSend);
                }
            }
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e)
    {
        System.out.println("[Server]: "+e.getMessage());
    }

    @Override
    public void onStart()
    {
        System.out.println(
                """
                    
                    ======================
                        Server started
                    ======================
                    
                    """
        );
    }
}
