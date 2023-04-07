package chat.sprchat.logic.socket;

import chat.sprchat.SprchatApplication;
import chat.sprchat.state.ConnectedClient;
import chat.sprchat.state.InetSocket;
import lombok.val;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Server extends WebSocketServer
{
    @EventListener(ApplicationReadyEvent.class)
    public void CONSOLE() throws InterruptedException
    {
        val scn = new Scanner(System.in);
        while(true)
        {
            val com = scn.nextLine();
            switch(com.toLowerCase())
            {
                case "server_stop" -> {
                    this.stop();
                    System.out.println("[Server]: successfully closed");
                }
                case "server_start" -> {
                    this.start();
                    System.out.println("[Server]: successfully started");
                }
            }
        }
    }

    public Server(InetSocket address)
    {
        super(address);
        this.start();
        System.out.println(InetSocket.ip);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake)
    {
        val newClient = new ConnectedClient(webSocket, clientHandshake);
        SprchatApplication.clients.add(newClient);
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
