package chat.sprchat.logic.socket;

import chat.sprchat.SprchatApplication;
import chat.sprchat.logic.util.UtilKt;
import chat.sprchat.state.ConnectedClient;
import chat.sprchat.state.InetSocket;
import chat.sprchat.state.LoadedMessage;
import chat.sprchat.state.orm.MsgRepo;
import com.google.gson.Gson;
import lombok.val;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.stereotype.Component;

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
            SprchatApplication.loadedMessages.add(new LoadedMessage(
                    msg.getUser(),msg.getMessage(), msg.getDate(), msg.getId())
            );
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
        UtilKt.handleMessage(webSocket, s, msgRepo);
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
