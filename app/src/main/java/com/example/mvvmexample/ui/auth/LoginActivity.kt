package com.example.mvvmexample.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmexample.R
import com.example.mvvmexample.databinding.ActivityLoginBinding
import com.example.mvvmexample.util.hide
import com.example.mvvmexample.util.show
import com.example.mvvmexample.util.toast
import kotlinx.android.synthetic.main.activity_login.progress_bar

//MVVM -> Activity can only communicate with ViewModel
// ViewModel -> will interact with repository to send the email/password to our server.

class LoginActivity : AppCompatActivity() , AuthListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        // get binding
        val dataBinding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        // get view model
        val viewModel = ViewModelProviders.of(this)[AuthViewModel::class.java]

        dataBinding.viewmodel = viewModel // it will bind our data with ui

        viewModel.authListener = this

    }

    override fun onStarted() {
        progress_bar.show()
        toast("Login started")
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(
            this, Observer {
                progress_bar.hide()
                toast(it)
            }
        )
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message)
    }
}

