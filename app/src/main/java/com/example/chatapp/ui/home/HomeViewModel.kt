package com.example.chatapp.ui.home

import com.example.chatapp.UserProvider
import com.example.chatapp.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : BaseViewModel<HomeNavigator>() {


    fun addRoom(){

        navigator?.openAddRoom()


    }

    fun logout(){
        val auth = FirebaseAuth.getInstance()
        auth.signOut()
        UserProvider.user = null
        navigator?.goToLogin()
    }


}