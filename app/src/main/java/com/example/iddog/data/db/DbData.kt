package com.example.iddog.data.db

import com.example.iddog.App
import com.example.iddog.data.AppDatabase
import com.example.iddog.data.DataSource
import com.example.iddog.model.Feed
import kotlin.reflect.KClass

object DbData {

    val db: AppDatabase by lazy { AppDatabase.getInstance(App.appContext()) }

    fun <Entity : Any> of(clazz: KClass<*>): DataSource<Entity> {
        return when (clazz) {
            Feed::class -> FeedDbData(db.getFeedDao())
            else -> throw IllegalArgumentException("Unsupported data type")
        } as DataSource<Entity>
    }

    fun clearDb() {
        db.clearAllTables()
    }

}
