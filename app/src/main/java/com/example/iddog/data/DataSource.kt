package com.example.iddog.data

import io.reactivex.Completable
import io.reactivex.Observable

interface DataSource<T : Any> {
    fun get(search: String): Observable<T>

    fun save(item: T): Observable<T>

    fun remove(item: T): Completable
}