package com.example.iddog.ui.signup


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.iddog.data.api.FeedApiData
import com.example.iddog.data.api.getDogService
import com.example.iddog.model.SignUpRequest
import com.example.iddog.model.SignUpResponse
import com.example.iddog.model.Token
import com.example.iddog.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    fun signUp(email: String, onSuccess: () -> Unit, onError: (String?) -> Unit) {

        val request = SignUpRequest(email)

        FeedApiData()
            .signUp(request)
            .enqueue(object : Callback<SignUpResponse> {
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                onError(t.message)
            }

            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                response.body()?.let {
                    Token(getApplication()).create(it.user.token)
                }
                onSuccess()
            }

        })

    }

    fun checkIfUserIsLogged(onSuccess: () -> Unit){
        if(Token(getApplication()).hasToken()){
            onSuccess()
        }
    }

}