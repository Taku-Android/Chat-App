package com.example.chatapp.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivitySignUpBinding
import com.example.chatapp.ui.base.BaseActivity
import com.example.chatapp.ui.login.LoginActivity
import com.example.chatapp.ui.login.LoginViewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding , SignUpViewModel>()
    , SignUpNavigator {

    override fun initViewModel(): SignUpViewModel {

        return  ViewModelProvider(this).get(SignUpViewModel::class.java)

    }

    override fun getLayoutId(): Int {

        return  R.layout.activity_sign_up
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        viewModel.navigator = this

        viewBinding.vm = viewModel




    }

    override fun goToLogin() {
        val i = Intent(this , LoginActivity::class.java )
        startActivity(i)
        finish()
    }


}