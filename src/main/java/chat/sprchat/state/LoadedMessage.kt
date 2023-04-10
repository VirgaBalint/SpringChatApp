package chat.sprchat.state

import java.util.*

data class LoadedMessage(
        private var user: String,
        private var message: String,
        private var date: Date,
        private val id: Long
){
    fun getUser(): String{
        return user
    }
    fun setUser(_user: String){
        user = _user
    }
    fun getDate(): Date{
        return date
    }
    fun setDate(_date: Date){
        date = _date
    }
    fun getMessage(): String{
        return message
    }
    fun setMessage(_message: String){
        message = _message
    }
}