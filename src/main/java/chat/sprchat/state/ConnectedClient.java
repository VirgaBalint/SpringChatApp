package chat.sprchat.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

@Getter @Setter
public class ConnectedClient
{
    private WebSocket socket;
    private ClientHandshake clientHandshake;
    private String name;

    public ConnectedClient(WebSocket socket, ClientHandshake clientHandshake)
    {
        this.socket = socket;
        this.clientHandshake = clientHandshake;
    }
}
