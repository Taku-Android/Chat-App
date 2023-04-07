package com.example.chatapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.chatapp.R
import com.example.chatapp.database.models.Room
import com.example.chatapp.databinding.ActivityHomeBinding
import com.example.chatapp.ui.addRoom.AddRoomActivity
import com.example.chatapp.ui.base.BaseActivity
import com.example.chatapp.ui.chat.ChatRoomActivity
import com.example.chatapp.ui.login.LoginActivity

class HomeActivity : BaseActivity<ActivityHomeBinding , HomeViewModel>() , HomeNavigator {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel.navigator = this
        viewBinding.vm = viewModel
        initializeAdapter()
        subscribeToLiveData()
        viewModel.loadRooms()


    }

    override fun onResume() {
        super.onResume()
        viewModel.loadRooms()

    }



    private fun subscribeToLiveData() {

        viewModel.roomsLiveData.observe(this){
            adapter.bindList(it)
        }

    }


    val adapter = RoomAdapter()
    private fun initializeAdapter() {

        viewBinding.content.roomsRv.adapter = adapter
        adapter.onItemClickListener = object : RoomAdapter.OnItemClickListener{
            override fun onItemClick(postion: Int, item: Room) {
                val chatIntent = Intent(this@HomeActivity , ChatRoomActivity::class.java)
                chatIntent.putExtra("room" , item)
                startActivity(chatIntent)
            }

        }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(): HomeViewModel {
        return  ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun openAddRoom() {
        val i = Intent(this , AddRoomActivity::class.java )
        startActivity(i)
    }

    override fun goToLogin() {
        val i = Intent(this , LoginActivity::class.java )
        startActivity(i)
        finish()
    }
}