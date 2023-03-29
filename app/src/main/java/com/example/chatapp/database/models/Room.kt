package com.example.chatapp.database.models

import android.graphics.Bitmap

data class Room(
    var id: String? = null ,
    var title: String? = null ,
    var description: String? = null ,
    var image: String? = null ,
    var createdBy: String? = null ,

)