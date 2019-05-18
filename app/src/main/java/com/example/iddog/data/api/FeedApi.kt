package com.example.iddog.data.api

import android.util.Log
import com.example.iddog.App.Companion.appContext
import com.example.iddog.data.DataSource
import com.example.iddog.model.Feed
import com.example.iddog.model.Token
import io.reactivex.Completable
import io.reactivex.Observable

class FeedApiData : DataSource<Feed> {

    private val token = Token(appContext()).request()


    private val api = APIClient<DogService>(token)
        .getClient(DogService::class.java)

    override fun get(search: String): Observable<Feed> {
        Log.d(this::class.toString(), "A api foi chamada")
        val retorno = api.feed(search)
        Log.d(this::class.toString(), retorno.toString())

        return retorno
    }

    override fun save(item: Feed): Observable<Feed> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(item: Feed): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}