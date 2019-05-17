package com.example.iddog.ui.image

import android.util.Log
import com.example.iddog.api.getDogService
import com.example.iddog.model.Feed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageListViewModel {
    fun getList(category: String?, onSuccess: (List<String>) -> Unit, onError: (String?) -> Unit){

        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJpZGRvZy1zZXJ2aWNlIiwic3ViIjoiNWNkZTIzOWI1YTk0MDExMjM3YTVkMmFhIiwiaWF0IjoxNTU4MDYxOTc5LCJleHAiOjE1NTkzNTc5Nzl9.90lxR7WOlf04bM803PVNq9xqu0-rYlc2cajUwEPmMS4"

        getDogService(token).feed(category).enqueue(object : Callback<Feed> {
            override fun onFailure(call: Call<Feed>, t: Throwable) {
                onError(t.message)
            }

            override fun onResponse(
                call: Call<Feed>,
                response: Response<Feed>
            ) {
                response.body()?.let {
                    val list: List<String> = it.list
                    Log.d("response user", it.toString())
                    onSuccess(list)
                }

            }

        })

    }
}