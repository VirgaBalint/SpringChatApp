package chat.sprchat.logic.util

import chat.sprchat.SprchatApplication
import chat.sprchat.state.LoadedMessage
import org.java_websocket.WebSocket

fun handleMessage(websocket: WebSocket, s: String)
{
    when(s)
    {
        "#connectedUsers" -> websocket.send(
                "connectedUsers#" +
                SprchatApplication.clients.size
        )
        else -> {
            if(s.startsWith("#setName"))
            {
                val name = s.substring("#setName:".length)
                for(c in SprchatApplication.clients)
                {
                    if(c.socket == websocket)
                        c.name = name
                }
            }
            else if(s.startsWith("#msg:"))
            {
                val msg = s.substring("#msg:".length)
                val data = msg.split(":")
                var username = ""
                for (c in SprchatApplication.clients)
                    if (c.socket === webSocket)
                        username = c.name
                val newMessage: LoadedMessage? = null
            }
        }
    }
}