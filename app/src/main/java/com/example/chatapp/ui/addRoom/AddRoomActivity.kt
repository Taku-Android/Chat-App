package com.example.chatapp.ui.addRoom

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityAddRoomBinding
import com.example.chatapp.ui.base.BaseActivity
import java.security.Provider

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding, AddRoomViewModel>() , AddRoomNavigator {

    override fun getLayoutId(): Int {

        return  R.layout.activity_add_room
    }

    override fun initViewModel(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }

    //get photo from device
    var pickedPhoto : Uri? = null
    var pickedBitmap : Bitmap? = null
    val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.navigator = this
        viewBinding.vm = viewModel


    }

    fun pickPhoto(view: View){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)

    }
    // handle the result of the image request
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data // get the image Uri from the Intent
            val inputStream = contentResolver.openInputStream(imageUri!!) // get an InputStream from the Uri
            val bitmap = BitmapFactory.decodeStream(inputStream) // decode the InputStream into a Bitmap
            viewBinding.content.roomImage.setImageBitmap(bitmap) // set the Bitmap to the ImageView
        }
    }


}