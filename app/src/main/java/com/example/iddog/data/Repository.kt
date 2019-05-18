package com.example.iddog.data

import com.example.iddog.App.Companion.isNetworkAvailable
import com.example.iddog.data.db.DbData
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

object Repository {

    inline fun <reified Entity : Any> of(): Repo<Entity> {
        return Repo<Entity>(ApiData.of(Entity::class), DbData.of(Entity::class))
    }

    fun clearDatabase(): Completable {
        return Completable.fromCallable { DbData.clearDb() }
            .subscribeOn(Schedulers.io())
    }
}

class Repo<T : Any>(val api: DataSource<T>, val db: DataSource<T>) : DataSource<T> {

    override fun save(item: T): Observable<T> {
        return Observable.defer {
            db.save(item)
        }
    }

    override fun remove(item: T): Completable {
        return Completable.defer {
            db.remove(item)
        }
    }

    override fun get(search: String): Observable<T>{
        return Observable.concatArrayEager(
            db.get(search).subscribeOn(Schedulers.io()),
            Observable.defer {
                if(isNetworkAvailable())
                    api.get(search).subscribeOn(Schedulers.io())
                        .flatMap { l ->
                            db.get(search)
                                .flatMapCompletable { old -> db.remove(old)}
                                .andThen(db.save(l))
                        }
                else
                    Observable.empty()
            }.subscribeOn(Schedulers.io())
        )
    }
}