package com.example.iddog.data.db

import com.example.iddog.App
import com.example.iddog.data.AppDatabase
import com.example.iddog.data.DataSourceDb
import com.example.iddog.model.Feed
import kotlin.reflect.KClass

object DbData {

    private val db: AppDatabase by lazy { AppDatabase.getInstance(App.appContext()) }
    fun <Entity : Any> of(clazz: KClass<*>): DataSourceDb<Entity> {
        @Suppress("UNCHECKED_CAST")
        return when (clazz) {
            Feed::class -> FeedDbData(db.getFeedDao())
            else -> throw IllegalArgumentException("Unsupported data type")
        } as DataSourceDb<Entity>
    }
}
