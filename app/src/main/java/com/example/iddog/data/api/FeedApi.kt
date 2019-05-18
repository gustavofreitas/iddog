package com.example.iddog.data.api

import com.example.iddog.App.Companion.appContext
import com.example.iddog.model.Feed
import com.example.iddog.model.SignUpRequest
import com.example.iddog.model.SignUpResponse
import com.example.iddog.model.Token
import io.reactivex.Observable
import retrofit2.Call

class FeedApiData {

    private val token = Token(appContext()).request()

    private val api = APIClient<DogService>(token)
        .getClient(DogService::class.java)

    fun get(search: String): Observable<Feed> {
        return api.feed(search)
    }

    fun signUp(request: SignUpRequest): Call<SignUpResponse> {
        return api.signUp(request)
    }
}