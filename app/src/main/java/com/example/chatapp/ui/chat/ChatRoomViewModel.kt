package com.example.chatapp.ui.chat

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.chatapp.UserProvider
import com.example.chatapp.database.FireBaseUtils
import com.example.chatapp.database.models.Message
import com.example.chatapp.database.models.Room
import com.example.chatapp.ui.base.BaseViewModel
import com.google.firebase.Timestamp

class ChatRoomViewModel : BaseViewModel<ChatRoomNavigator>() {

    var room:Room? = null
    val messageField = ObservableField<String>()



    fun back(){
        navigator?.closeChat()
    }

    fun sendMessage(){
        if (messageField.get().isNullOrBlank()) return

        val message = Message(
            content = messageField.get() ,
            senderId = UserProvider.user?.id ,
            senderName = UserProvider.user?.userName ,
            roomId = room?.id ,
            dateTime = Timestamp.now()
        )

        FireBaseUtils()
            .sendMessage(message)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    messageField.set("")
                    return@addOnCompleteListener
                }
                navigator?.showMessage("error sending your message",
                    posActionTitle = "try again" ,
                    posAction = {
                        sendMessage()
                    }
                )
            }

    }

}