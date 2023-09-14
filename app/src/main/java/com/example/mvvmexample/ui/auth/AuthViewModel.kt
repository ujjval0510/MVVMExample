package com.example.mvvmexample.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmexample.data.repositories.UserRepository
import com.example.mvvmexample.util.APIException
import com.example.mvvmexample.util.Corountines
import com.example.mvvmexample.util.NoInternetException

/**
 * AuthViewModel Class will interact with our
 * repository to perform the actual login operation.
 */
class AuthViewModel(
    private val repository: UserRepository // constructor injection
) : ViewModel(){

    var email : String ? = null
    var password : String? = null
    var confirmpassword : String? = null
    var name : String ? = null

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
            try {
                val authResponse = repository.userLogin(email!!, password!!) // calling suspending function inside coroutine
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it) // save user in database
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e:APIException) {
                authListener?.onFailure(e.message!!)
            } catch (e:NoInternetException) {
                authListener?.onFailure(e.message!!)
            }

        }

    }

    fun getLoggedInUser() = repository.getUser()

    fun onSignIn(view: View) {
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onSignup(view:View) {
        Intent(view.context,SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onSignupButtonClick(view:View) {
        authListener?.onStarted()
        if(name.isNullOrEmpty()) {
            authListener?.onFailure("Name is required")
            return
        }
        if(email.isNullOrEmpty()) {
            authListener?.onFailure("Email is required")
            return
        }

        if(password.isNullOrEmpty()) {
            authListener?.onFailure("Please enter password")
            return
        }

        if(password != confirmpassword) {
            authListener?.onFailure("Password not match")
            return
        }


    }
}