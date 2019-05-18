package com.example.iddog.data.api

import com.example.iddog.model.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface DogService {

    @POST("/signup")
    fun signUp(@Body jobId: SignUpRequest): Call<SignUpResponse>

    @GET("/feed")
    fun feed(
        @Query("category") category: String?
    ): Observable<Feed>

}
