package com.example.mvvmexample.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.mvvmexample.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    private val context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isInternetAvaliable())
            throw NoInternetException("No Internet connection.")

        return chain.proceed(chain.request())
    }

    private fun isInternetAvaliable() : Boolean {
        val connectivityManager = applicationContext.
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it!=null && it.isConnected
        }
    }
}