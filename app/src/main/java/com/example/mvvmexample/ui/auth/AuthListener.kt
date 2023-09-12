package com.example.mvvmexample.ui.auth

import androidx.lifecycle.LiveData
import com.example.mvvmexample.data.db.entity.User
import com.example.mvvmexample.data.network.responses.AuthResponse
import retrofit2.Response

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message : String)
}