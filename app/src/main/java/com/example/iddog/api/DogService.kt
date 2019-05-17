package com.example.iddog.api

import com.example.iddog.model.*
import retrofit2.Call
import retrofit2.http.*

interface DogService {

    @POST("/signup")
    fun signUp(@Body jobId: SignUpRequest): Call<SignUpResponse>

    @GET("/feed")
    fun feed(
        @Query("category") category: String?
    ): Call<Feed>

}
