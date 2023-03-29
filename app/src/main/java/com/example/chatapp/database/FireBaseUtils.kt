package com.example.chatapp.database

import com.example.chatapp.database.models.Room
import com.example.chatapp.database.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class FireBaseUtils {

    val userCollectionName = "users"
    val roomCollectionName = "rooms"

    fun getUsersCollection():CollectionReference{
        val database = FirebaseFirestore.getInstance()
        return database.collection(userCollectionName)
    }
    fun getRoomCollection():CollectionReference{
        val database = FirebaseFirestore.getInstance()
        return database.collection(roomCollectionName)
    }

    fun insertUserToDataBase(user: User): Task<Void> {

       val docRef = getUsersCollection()
            .document(user.id!!)

        return docRef.set(user)
    }

    fun getUserFromDataBase(uid:String): Task<DocumentSnapshot>{
        val docRef = getUsersCollection()
            .document(uid)

        return docRef.get()
    }

    fun insertRoom(room: Room): Task<Void> {



        var docRef = getRoomCollection().document()

        room.id = docRef.id

        return  docRef.set(room)


    }


}