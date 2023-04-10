package chat.sprchat.state

import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake

data class ConnectedClient(
        private val socket: WebSocket,
        private val clientHandshake: ClientHandshake,
){
    private lateinit var name: String
    fun getSocket(): WebSocket{
        return socket
    }
    fun getClientHandshake(): ClientHandshake{
        return clientHandshake
    }
    fun getName(): String{
        return name
    }
    fun setName(_name: String){
        name = _name
    }
}