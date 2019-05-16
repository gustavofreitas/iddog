package com.example.iddog.api

import com.example.iddog.model.SignUpRequest
import com.example.iddog.model.SignUpResponse
import com.example.iddog.model.User
import retrofit2.Call
import retrofit2.http.*

interface DogService {

    @Headers("Content-Type: application/json")
    @POST("/signup")
    fun postSignUp(@Body jobId: SignUpRequest): Call<SignUpResponse>

    @GET("/feed")
    fun getPhotos(
        @Query("category") category: String
    ): List<User>

}
