const connectedUsers = document.getElementById("connected-users")
const usernameInit = document.getElementById("username")
const setUsername = document.getElementById("setUsername")
const table = document.getElementById('chat')
const tbody = table.querySelector('tbody')
tbody.scrollTop = tbody.scrollHeight

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
        socket = new WebSocket("ws://localhost:8081")
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
                const msg = temp.substring('#newMsg'.length)
                const jsonObj = JSON.parse(msg)
                console.log(jsonObj)

                const existingRows = Array.from(table.querySelectorAll('tr'))
                tbody.innerHTML = ''
                jsonObj.forEach(text => {
                    const existingRow = existingRows.find(row => row.dataset.id === text.id)
                    const tr = document.createElement('tr')
                    tr.dataset.id = text.id
                    tr.innerHTML = '<td>' + text.date + '</td><td>' + text.user + '</td><td id="msg">' + text.message + '</td>'+
                        '<td sec:authorize="hasAuthority(\'ROLE_ADMIN\')">'+
                        '<form method="post" th:action="@{/msg-delete/${text.id}}">'+
                        '<input type="hidden" name="text_id" th:value="${text.id}">'+
                        '<input id="delete" type="submit" role="button" value="X">'+
                        '</form></td>'
                    table.querySelector('tbody').appendChild(tr)

                })
                tbody.scrollTop = tbody.scrollHeight
            }
        })

        socket.addEventListener("error", event => {
            console.error("Server connection error:",event);
        })
        socket.addEventListener("close", event => {
            
        })

        const msgDelete = document.getElementById("delete")
        msgDelete.addEventListener("click", () => {
            socket.send("#delete")
        })

        sendMessage.addEventListener('click', event =>{         // Send message button

            if(username != null){
                const time = new Date()
                let msg = message.value
                if(msg === ''){
                    alert('You cannot send an empty message')
                }
                else{
                    message.value = ""
                    socket.send("#msg:"+msg+":"+time)
                }
            }
            else{
                alert("Please enter a username")
            }
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