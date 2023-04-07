package com.example.chatapp.ui.chat

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.database.models.Room
import com.example.chatapp.databinding.ActivityChatRoomBinding
import com.example.chatapp.ui.base.BaseActivity
import com.example.chatapp.ui.home.HomeViewModel
import java.security.Provider

class ChatRoomActivity : BaseActivity<ActivityChatRoomBinding, ChatRoomViewModel>() , ChatRoomNavigator {


    override fun getLayoutId(): Int {
       return R.layout.activity_chat_room
    }

    override fun initViewModel(): ChatRoomViewModel {
        return ViewModelProvider(this).get(ChatRoomViewModel::class.java)
    }

     var room : Room? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.navigator = this
        viewBinding.vm = viewModel

        initializeRoom()



    }

    fun initializeRoom(){
        room = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("room" , Room::class.java)
        }else{
            intent.getParcelableExtra("room")
        }
    }


}