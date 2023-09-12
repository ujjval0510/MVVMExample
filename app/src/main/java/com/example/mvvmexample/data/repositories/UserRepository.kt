package com.example.mvvmexample.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmexample.data.network.MyAPI
import com.example.mvvmexample.data.network.SafeAPIRequest
import com.example.mvvmexample.data.network.responses.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository : SafeAPIRequest() {

/*    fun userLogin(email:String, password:String) : LiveData<String> {
        // we can not create a instant of LiveData because it's abstract class
        // we will use MutableLiveData

        val loginResponse = MutableLiveData<String>()

        // bad practice to call MYAPI userLogin method
        MyAPI().userLogin(email, password).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful) {
                    loginResponse.value = response.body()?.string()
                }else {
                    loginResponse.value = response.errorBody()?.toString()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                loginResponse.value = t.message
            }

        })
        return loginResponse
    }*/


 /*   suspend fun userLogin(email:String, password:String) : Response<AuthResponse> {
       return MyAPI().userLogin(email, password)
    }*/
     suspend fun userLogin(email:String, password:String) : AuthResponse {
        // using generic functions
         return apiRequest { MyAPI().userLogin(email, password) }
     }
}