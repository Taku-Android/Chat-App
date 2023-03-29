package com.example.chatapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.chatapp.R
import com.example.chatapp.UserProvider
import com.example.chatapp.database.FireBaseUtils
import com.example.chatapp.database.models.User
import com.example.chatapp.ui.home.HomeActivity
import com.example.chatapp.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed(Runnable { //This method will be executed once the timer is over
            // Start your app main activity
            navigate()
            // close this activity

        }, 2000)
    }

    private fun navigate() {
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            val i = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(i)
            return
        }
        FireBaseUtils()
            .getUserFromDataBase(auth.currentUser!!.uid)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    val i = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                    return@addOnCompleteListener

                }
                val user = it.result.toObject(User::class.java)
                UserProvider.user = user
                val i = Intent(this@SplashActivity, HomeActivity::class.java)
                startActivity(i)
                finish()


            }


    }
}