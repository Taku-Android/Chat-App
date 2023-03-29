package com.example.chatapp.ui.addRoom

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import com.example.chatapp.UserProvider
import com.example.chatapp.database.FireBaseUtils
import com.example.chatapp.database.models.Room
import com.example.chatapp.ui.base.BaseViewModel
import com.example.chatapp.ui.isValidEmail

class AddRoomViewModel : BaseViewModel<AddRoomNavigator>() {

    var image: String? = null
    val title = ObservableField<String>()
    var titleError = ObservableField<String?>()
    val description = ObservableField<String>()
    var descriptionError = ObservableField<String?>()


    fun createRoom() {
        if (!validForm()) return

        val room = Room(
            title = title.get(),
            description = description.get(),
            createdBy = UserProvider.user?.userName,
            image = image,
        )

        navigator?.showLoading("Loading...")
        FireBaseUtils()
            .insertRoom(room)
            .addOnCompleteListener {
                navigator?.hideDialog()
                if (it.isSuccessful) {
                    navigator?.showMessage(
                        "room added successfully",
                        posActionTitle = "ok",
                        posAction = {
                            goBack()
                        })

                } else {
                    navigator?.showMessage(it.exception!!.localizedMessage)
                    return@addOnCompleteListener
                }
            }

    }

    fun goBack() {
        title.set("")
        description.set("")
        navigator?.gotToHome()
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
        if (image.isNullOrBlank()) {
            navigator?.showMessage("enter image please")
            isValid = false
        } else {

            isValid = true
        }

        return isValid
    }


}