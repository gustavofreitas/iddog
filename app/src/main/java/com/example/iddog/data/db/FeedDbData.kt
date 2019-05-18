package com.example.iddog.data.db

import com.example.iddog.data.DataSourceDb
import com.example.iddog.data.entity.FeedEntity
import com.example.iddog.model.Feed
import io.reactivex.Completable
import io.reactivex.Observable

class FeedDbData(private val dao: FeedDao) : DataSourceDb<Feed> {
    override fun get(search: String): Observable<Feed> {
        val feeds = dao.getAll(search)
        return Observable.just(Feed(search, feeds.map { it.url }))

    }

    override fun save(item: Feed): Observable<Feed> {
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