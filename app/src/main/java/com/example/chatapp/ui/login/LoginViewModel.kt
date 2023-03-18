package com.example.chatapp.ui.login

import androidx.databinding.ObservableField
import com.example.chatapp.ui.base.BaseViewModel
import com.example.chatapp.ui.isValidEmail
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : BaseViewModel<LoginNavigator>()  {


    val email = ObservableField<String>()
    var emailError = ObservableField<String?>()
    val password = ObservableField<String>()
    var passwordError = ObservableField<String?>()

    val auth = FirebaseAuth.getInstance()

    fun login(){

        if(!validForm())return

        navigator?.showLoading("Loading...")
        auth.signInWithEmailAndPassword(email.get()!! , password.get()!!)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    navigator?.hideDialog()
                    navigator?.showMessage("Successful login")
                }else{
                    navigator?.hideDialog()
                    navigator?.showMessage(it.exception?.localizedMessage ?: "")

                }
            }


    }


    private fun validForm(): Boolean {
        var isValid = true
        if (email.get().isNullOrBlank()){
            emailError.set("Please enter your email")
            isValid = false
        }else if (email.get()?.isValidEmail() == false){
            emailError.set("Please enter correct email")
            isValid = false
        }else{
            emailError.set(null)
            isValid = true
        }
        if (password.get().isNullOrBlank()){
            passwordError.set("Please enter your password")
            isValid = false
        }else{
            passwordError.set(null)
            isValid = true
        }

        return  isValid
    }

    fun goToSignUp(){
        navigator?.goToSignUp()
    }

}