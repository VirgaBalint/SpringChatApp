package chat.sprchat.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

@Getter @Setter @AllArgsConstructor
public class ConnectedClient
{
    WebSocket socket;
    ClientHandshake clientHandshake;
}
