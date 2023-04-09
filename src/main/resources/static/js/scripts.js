import $ from '/jquery-3.6.4.min'

const connectedUsers = document.getElementById("connected-users")
const usernameInit = document.getElementById("username")
const setUsername = document.getElementById("setUsername")

let message = document.getElementById("message")
let sendMessage = document.getElementById("sendMessage")
message.disabled = true
sendMessage.disabled = true

let socket

let username
let usernameField = document.getElementById("usernameTag")
setUsername.addEventListener('click', () => {               // Set username, connect to server
    const inputVal = usernameInit.value
    if(inputVal.length < 4){
        alert("Username must be at least 3 characters")
    }
    else {
        username = inputVal
        console.log("username set:",inputVal);
        usernameField.textContent = ("Username: " + inputVal)
        usernameInit.disabled = true
        setUsername.disabled = true

        message.disabled = false
        sendMessage.disabled = false
        
        // Client
        socket = new WebSocket("ws://192.168.1.69:8081")
        socket.addEventListener("open", event => {
            console.log("Connected to server")
            socket.send("#connectedUsers")
            socket.send("#setName:"+username)
        
            const time = new Date()
            document.getElementById("current-time").innerHTML = time.toLocaleTimeString()
        })
        
        socket.addEventListener("message", event => {
            console.log("Received message from server:",event.data)
            
            const temp = event.data
            if(temp.startsWith("connectedUsers#")){
                connectedUsers.textContent = event.data.substring("connectedUsers#".length)
            }
            if(temp.startsWith("#newMsg")){
                refreshTable()
            }
        })
        
        socket.addEventListener("error", event => {
            console.error("Server connection error:",event);
        })
        socket.addEventListener("close", event => {
            
        })

        sendMessage.addEventListener('click', event =>{
            const time = new Date()
            const currentTime = time.toLocaleTimeString()
            let msg = message.value
            message.value = ""
            socket.send("#msg:"+msg+":"+currentTime)
        })
        
        setInterval(() => {                 // Update connected users every 5 seconds
            socket.send("#connectedUsers") 
        }, 5000);
    }
})

function updateTime() {
    const time = new Date()
    document.getElementById("current-time").innerHTML = time.toLocaleTimeString()
  }
setInterval(updateTime, 1000)

function refreshTable() {
    $.ajax({
        url: '/getMsg',
        dataType: 'json',
        success: function(data){
            let tableBody = $('#chat tbody')
            tableBody.empty()
            $.each(data, function(i, message){
                let row = $('<tr>').appendTo(tableBody)
                $('<td>').text(message.date).appendTo(row)
                $('<td>').text(message.user).appendTo(row)
                $('<td>').text(message.message).appendTo(row)
            })
        }
    })
}