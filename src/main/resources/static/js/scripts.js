function updateTime() {
    const time = new Date()
    const currentTime = time.toLocaleTimeString()
    document.getElementById("current-time").innerHTML = currentTime
  }
  setInterval(updateTime, 1000)

// Client
const socket = new WebSocket("ws://192.168.1.69:8081")
socket.addEventListener("open", event => {
    console.log("Connected to server")
})

socket.addEventListener("message", event => {
    console.log("Received message from server:",event.data)
})

socket.addEventListener("error", event => {
    console.error("Server connection error:",event);
})

socket.addEventListener("close", event => {

})