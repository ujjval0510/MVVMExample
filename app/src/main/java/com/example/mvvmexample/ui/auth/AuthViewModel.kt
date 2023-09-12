package com.example.mvvmexample.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmexample.data.repositories.UserRepository
import com.example.mvvmexample.util.Corountines

/**
 * AuthViewModel Class will interact with our
 * repository to perform the actual login operation.
 */
class AuthViewModel : ViewModel(){

    var email : String ? = null
    var password : String? = null
    var authListener:AuthListener? = null;
    fun onLoginButtonClick(view:View) {
        //started
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()) {
            //failure
            authListener?.onFailure("Invalid email or password")
            return;
        }

        // We can't access UserRepository().userLogin directly because it's dependent on UserRepository class it's tight coupling
        // so instead of this we need to use Dependency injection.
        /*val loginResponse = UserRepository().userLogin(email!!,password!!)
        authListener?.onSuccess(loginResponse)*/

        // Using coroutine
        Corountines.main {
            val response = UserRepository().userLogin(email!!, password!!) // calling suspending function inside coroutine
            if(response.isSuccessful){
                authListener?.onSuccess(response.body()?.user!!)
            }else {
                authListener?.onFailure("Response Code : ${response.code()}")
            }
        }

    }
}