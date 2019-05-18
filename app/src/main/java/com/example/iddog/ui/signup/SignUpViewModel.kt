package com.example.iddog.ui.signup

import androidx.lifecycle.ViewModel
import com.example.iddog.App
import com.example.iddog.data.api.DogApiData
import com.example.iddog.model.SignUpRequest
import com.example.iddog.model.SignUpResponse
import com.example.iddog.model.Token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {

    fun signUp(email: String, onSuccess: () -> Unit, onError: (String?) -> Unit) {

        val request = SignUpRequest(email)

        DogApiData()
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
                    Token(App.appContext()).create(it.user.token)
                }
                onSuccess()
            }

        })

    }

    fun checkIfUserIsLogged(onSuccess: () -> Unit){
        if(Token(App.appContext()).hasToken()){
            onSuccess()
        }
    }

}