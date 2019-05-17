package com.example.iddog.api

import android.content.Context
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient<T>(val token: String? = null) {

    private val API_BASE_URL: String = "https://api-iddog.idwall.co"

    fun getClient(c: Class<T>): T {
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(ServiceInterceptor(token)).build()

        val builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return builder.create(c)
    }

}

class ServiceInterceptor(val token: String?) : Interceptor {

    override fun intercept(chain: Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader("Content-Type","application/json")

        if (!token.isNullOrEmpty()){
            requestBuilder.addHeader("Authorization", token.toString())
        }

        return chain.proceed(requestBuilder.build())
    }

}

private var picasso: Picasso? = null
fun getPicasso(context: Context) : Picasso {
    if (picasso == null) {
        picasso = Picasso
            .Builder(context)
            .build()
    }
    return picasso!!
}

fun getDogService(): DogService {
    return APIClient<DogService>()
        .getClient(DogService::class.java)
}

fun getDogService(token: String): DogService {
    return APIClient<DogService>(token)
        .getClient(DogService::class.java)
}