package com.example.iddog.data.api

import com.example.iddog.App
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient<T>(val token: String? = null) {

    private val BASE_URL: String = "https://api-iddog.idwall.co"

    fun getClient(c: Class<T>): T {

        val myCache = Cache(App.appContext().cacheDir, (50 * 1024).toLong())

        val client: OkHttpClient = OkHttpClient
            .Builder()
            .cache(myCache)
            .addInterceptor(ServiceInterceptor(token)).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(c)

    }

    class ServiceInterceptor(private val token: String?) : Interceptor {

        override fun intercept(chain: Chain): Response {
            chain.request().newBuilder().apply {
                addHeader("Content-Type", "application/json")

                if (App.isNetworkAvailable())
                    addHeader("Cache-Control", "public, max-age=" + 60)
                else
                    addHeader(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + (60 * 60 * 24 * 7)
                    )

                if (token?.isNotEmpty() == true)
                    addHeader("Authorization", token.toString())


                return chain.proceed(build())
            }
        }

    }
}

