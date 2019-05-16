package com.example.iddog.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient<T> {

    fun getClient(c: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-iddog.idwall.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    return retrofit.create(c)
    }
}

fun getDogService(): DogService {
    return APIClient<DogService>()
        .getClient(DogService::class.java)
}