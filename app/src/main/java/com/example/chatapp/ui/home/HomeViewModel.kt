package com.example.chatapp.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.chatapp.UserProvider
import com.example.chatapp.database.FireBaseUtils
import com.example.chatapp.database.models.Room
import com.example.chatapp.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : BaseViewModel<HomeNavigator>() {

    val roomsLiveData = MutableLiveData<List<Room>>()

    fun addRoom(){

        navigator?.openAddRoom()


    }

    fun logout(){
        val auth = FirebaseAuth.getInstance()
        auth.signOut()
        UserProvider.user = null
        navigator?.goToLogin()
    }

    fun loadRooms(){
        FireBaseUtils()
            .getRooms()
            .addOnCompleteListener {
                if (!it.isSuccessful){
                    navigator?.showMessage("error loading rooms")
                    return@addOnCompleteListener
                }
                val rooms = it.result.toObjects(Room::class.java)
                roomsLiveData.value = rooms
            }

    }

}