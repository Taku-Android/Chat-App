package com.example.chatapp.ui.addRoom

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import com.example.chatapp.ui.base.BaseViewModel
import com.example.chatapp.ui.isValidEmail

class AddRoomViewModel : BaseViewModel<AddRoomNavigator>() {

    val title = ObservableField<String>()
    var titleError = ObservableField<String?>()
    val description = ObservableField<String>()
    var descriptionError = ObservableField<String?>()




    fun createRoom(){
        if (!validForm()) return
    }


    private fun validForm(): Boolean {
        var isValid = true
        if (title.get().isNullOrBlank()) {
            titleError.set("Please enter your email")
            isValid = false
        } else {
            titleError.set(null)
            isValid = true
        }
        if (description.get().isNullOrBlank()) {
            descriptionError.set("Please enter your password")
            isValid = false
        } else {
            descriptionError.set(null)
            isValid = true
        }

        return isValid
    }

    fun addImage(){

    }






}