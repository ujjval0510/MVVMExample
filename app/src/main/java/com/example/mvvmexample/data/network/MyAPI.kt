package com.example.mvvmexample.data.network

import com.example.mvvmexample.data.network.responses.AuthResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyAPI {
    /**
     * Suspending functions are the center of the coroutines
     * Function that can be paused and resume at later time.
     * this type of function can execute long running operation and
     * wait for it to complete without blocking.
     */
    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin ( // add this function as suspend function.
        @Field("email") email : String,
        @Field("password") password : String
    ) : Response<AuthResponse>

    @FormUrlEncoded
    @POST("/signup")
    suspend fun userSignup(
        @Field("email") email : String,
        @Field("password") password: String,
        @Field("confirmpassword") confirmPassword:String
    ): Response<AuthResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) :MyAPI {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://api.localhost.in/mvvm/")
                .client(okHttpClient) // added interceptor for checking connection
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyAPI::class.java)
        }
    }
}