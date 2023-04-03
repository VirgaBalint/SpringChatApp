package chat.sprchat.state;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.stereotype.Component;

@Component
public class Server extends WebSocketServer
{
    public Server(InetSocket address)
    {
        super(address);
        this.start();
        System.out.println(InetSocket.ip);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake)
    {

    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b)
    {

    }

    @Override
    public void onMessage(WebSocket webSocket, String s)
    {

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
