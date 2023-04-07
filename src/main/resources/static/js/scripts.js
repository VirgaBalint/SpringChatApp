const connectedUsers = document.getElementById("connected-users")
const usernameInit = document.getElementById("username")
const setUsername = document.getElementById("setUsername")

let message = document.getElementById("message")
let sendMessage = document.getElementById("sendMessage")


let username
// Client
const socket = new WebSocket("ws://192.168.1.69:8081")
socket.addEventListener("open", event => {
    console.log("Connected to server")
    socket.send("#connectedUsers")

    const time = new Date()
    const currentTime = time.toLocaleTimeString()
    document.getElementById("current-time").innerHTML = currentTime
})

socket.addEventListener("message", event => {
    console.log("Received message from server:",event.data)
    
    const temp = event.data
    if(temp.startsWith("connectedUsers#")){
        const msg = event.data.substring("connectedUsers#".length)
        connectedUsers.textContent = msg
    }
})

socket.addEventListener("error", event => {
    console.error("Server connection error:",event);
})
socket.addEventListener("close", event => {
    
})

function updateTime() {
    const time = new Date()
    const currentTime = time.toLocaleTimeString()
    document.getElementById("current-time").innerHTML = currentTime
  }
setInterval(updateTime, 1000)

setInterval(() => {                 // Update connected users every 5 seconds
    socket.send("#connectedUsers") 
}, 5000);

let usernameField = document.getElementById("usernameTag")
setUsername.addEventListener('click', () => {
    const inputVal = usernameInit.value
    if(inputVal.length < 4){
        alert("Username must be at least 3 characters")
    }
    else{
        username = inputVal
        console.log("username set:",inputVal);
        usernameField.textContent = ("Username: " + inputVal)
        usernameInit.disabled = true
        setUsername.disabled = true  
    }
})