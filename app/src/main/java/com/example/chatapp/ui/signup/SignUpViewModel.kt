package com.example.chatapp.ui.signup

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.chatapp.database.FireBaseUtils
import com.example.chatapp.database.models.User
import com.example.chatapp.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel : BaseViewModel<SignUpNavigator>() {

    val name = ObservableField<String?>()
    var nameError = ObservableField<String?>()
    val email = ObservableField<String?>()
    var emailError = ObservableField<String?>()
    val password = ObservableField<String?>()
    var passwordError = ObservableField<String?>()
    val passwordConfirmation = ObservableField<String?>()
    var passwordConfirmationError = ObservableField<String?>()

    val auth = FirebaseAuth.getInstance()

    fun signUp() {

        if (!validForm()) return

        navigator?.showLoading("Loading...")
        auth.createUserWithEmailAndPassword(email.get()!!,
            password.get()!!)
            .addOnCompleteListener {task ->
                if(task.isSuccessful){
                   insertuserToDataBase(task.result.user?.uid)

                }else{
                    navigator?.hideDialog()
                    navigator?.showMessage(task.exception!!.localizedMessage)
                }
            }

    }

    private fun insertuserToDataBase(uid: String?) {

        val user = User(
            id = uid ,
            userName = name.get() ,
            email = email.get()
        )

        FireBaseUtils().insertUserToDataBase(user)
            .addOnCompleteListener {
                navigator?.hideDialog()
                if(it.isSuccessful){
                    navigator?.showMessage("Successful registration")
                    navigator?.goToLogin()
                }else{

                    navigator?.showMessage(it.exception!!.localizedMessage)

                }
            }


    }


    private fun validForm(): Boolean {
        var isValid = true
        if (email.get().isNullOrBlank()) {
            emailError.set("Please enter your email")
            isValid = false
        } else {
            emailError.set(null)
            isValid = true
        }
        if (name.get().isNullOrBlank()) {
            nameError.set("Please enter your name")
            isValid = false
        } else {
            nameError.set(null)
            isValid = true
        }
        if (passwordConfirmation.get().isNullOrBlank()) {
            passwordConfirmationError.set("Please confirm your password")
            isValid = false
        } else {
            passwordConfirmationError.set(null)
            isValid = true
        }
        if (password.get().isNullOrBlank()) {
            passwordError.set("Please enter your password")
            isValid = false
        } else {
            passwordError.set(null)
            isValid = true
        }

        return isValid
    }

    fun goToLogin() {
        navigator?.goToLogin()
    }


}