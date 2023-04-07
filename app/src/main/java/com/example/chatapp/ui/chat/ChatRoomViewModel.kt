package com.example.chatapp.ui.chat

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.chatapp.database.models.Room
import com.example.chatapp.ui.base.BaseViewModel

class ChatRoomViewModel : BaseViewModel<ChatRoomNavigator>() {

    val room:Room? = null
    val message = ObservableField<String>()



    fun back(){

    }

    fun sendMessage(){

    }

}