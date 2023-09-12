package com.example.mvvmexample.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmexample.R
import com.example.mvvmexample.data.db.AppDatabase
import com.example.mvvmexample.data.db.entity.User
import com.example.mvvmexample.data.network.MyAPI
import com.example.mvvmexample.data.repositories.UserRepository
import com.example.mvvmexample.databinding.ActivityLoginBinding
import com.example.mvvmexample.ui.home.HomeAcitivity
import com.example.mvvmexample.util.hide
import com.example.mvvmexample.util.show
import com.example.mvvmexample.util.snackBar
import com.example.mvvmexample.util.toast
import kotlinx.android.synthetic.main.activity_login.progress_bar
import kotlinx.android.synthetic.main.activity_login.root_layout

//MVVM -> Activity can only communicate with ViewModel
// ViewModel -> will interact with repository to send the email/password to our server.

class LoginActivity : AppCompatActivity() , AuthListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        val db = AppDatabase(this)
        val api = MyAPI();
        val repository = UserRepository(api,db)
        val factory = AuthViewModelFactory(repository)

        // get binding
        val dataBinding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        // get view model
        val viewModel = ViewModelProviders.of(this,factory)[AuthViewModel::class.java]

        dataBinding.viewmodel = viewModel // it will bind our data with ui

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(
            this, Observer { user ->
                if(user!=null) {
                    Intent(this,HomeAcitivity::class.java).also {
                        it.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(it)
                    }
                }
            }
        )
    }

    override fun onStarted() {
        progress_bar.show()
        toast("Login started")
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        root_layout.snackBar("${user.name} is logged in");
    }

    /*  override fun onSuccess(loginResponse: LiveData<String>) {
          loginResponse.observe(
              this, Observer {
                  progress_bar.hide()
                  toast(it)
              }
          )
      }*/

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackBar(message) // display snackbar
    }
}

