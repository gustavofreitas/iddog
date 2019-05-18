package com.example.iddog.data.db

import android.util.Log
import com.example.iddog.data.DataSource
import com.example.iddog.data.entity.FeedEntity
import com.example.iddog.model.Feed
import io.reactivex.Completable
import io.reactivex.Observable

class FeedDbData(val dao: FeedDao) : DataSource<Feed> {
    override fun get(search: String): Observable<Feed> {
        Log.d(this::class.toString(), "O Banco foi chamado")
        val feeds = dao.getAll(search)
        return Observable.just(Feed(search, feeds.map { it.url }))

    }

    override fun save(item: Feed): Observable<Feed> {
        Log.d(this::class.toString(), "Gravou os dados no banco")
        return Completable.fromCallable { dao.insertAll(item.toFeedEntity()) }
            .andThen(Observable.just(item))
    }

    override fun remove(item: Feed): Completable {
        return Completable.fromCallable { dao.deleteAll(item.toFeedEntity()) }
    }

    private fun Feed.toFeedEntity(): List<FeedEntity>{
        val feeds = mutableListOf<FeedEntity>()

        this.list.forEach {
            feeds.add(FeedEntity(this.category, it))
        }

        return feeds
    }

}