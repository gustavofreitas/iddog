package com.example.iddog.data

import com.example.iddog.App.Companion.isNetworkAvailable
import com.example.iddog.data.db.DbData
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

object Repository {

    inline fun <reified Entity : Any> of(): Repo<Entity> {
        return Repo(ApiData.of(Entity::class), DbData.of(Entity::class))
    }
}

class Repo<T : Any>(private val api: DataSource<T>, private val db: DataSourceDb<T>) : DataSource<T> {

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