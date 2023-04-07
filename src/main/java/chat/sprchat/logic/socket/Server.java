package chat.sprchat.logic.socket;

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
    public void CONSOLE()
    {
        val scn = new Scanner(System.in);
        while(true)
        {
            val com = scn.nextLine();
            switch(com.toLowerCase())
            {
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
