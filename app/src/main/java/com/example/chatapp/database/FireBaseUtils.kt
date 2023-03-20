package com.example.chatapp.database

import com.example.chatapp.database.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class FireBaseUtils {

    val userCollectionName = "users"

    fun getUsersCollection():CollectionReference{
        val database = FirebaseFirestore.getInstance()
        return database.collection(userCollectionName)
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




}