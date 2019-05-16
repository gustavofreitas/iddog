package com.example.iddog.ui

import android.util.Log
import com.example.iddog.api.getDogService
import com.example.iddog.model.SignUpRequest
import com.example.iddog.model.SignUpResponse
import com.example.iddog.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel {

    fun signUp(email: String, onSuccess: () -> Unit, onError: (String?) -> Unit) {

        val request = SignUpRequest(email)

        getDogService().postSignUp(request).enqueue(object : Callback<SignUpResponse> {
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                onError(t.message)
            }

            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                response.body()?.let {
                    val user: User = it.user
                    Log.d("response user", user.toString())
                }
                onSuccess()
            }

        })

    }

}