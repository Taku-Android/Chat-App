package com.example.chatapp.database.models

import com.google.firebase.Timestamp

data class Message (
    var id:String? = null ,
    var content:String? = null ,
    var roomId:String? = null ,
    var senderId:String? = null ,
    var senderName:String? = null ,
    var dateTime:Timestamp? = null ,
  //  var recieved : Boolean = false ,
   // var read: Boolean = false

)