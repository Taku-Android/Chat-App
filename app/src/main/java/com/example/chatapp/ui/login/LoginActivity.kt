package com.example.chatapp.ui.login

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityLoginBinding
import com.example.chatapp.ui.base.BaseActivity
import com.example.chatapp.ui.home.HomeActivity
import com.example.chatapp.ui.signup.SignUpActivity

class LoginActivity :
    BaseActivity<ActivityLoginBinding ,LoginViewModel>() , LoginNavigator {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        viewBinding.vm = viewModel
        viewModel.navigator = this



    }

    override fun goToSignUp() {
        val i = Intent(this , SignUpActivity::class.java)
        startActivity(i)

    }

    override fun goToHome() {

        val i = Intent(this , HomeActivity::class.java)
        startActivity(i)
        finish()

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }



    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }



}