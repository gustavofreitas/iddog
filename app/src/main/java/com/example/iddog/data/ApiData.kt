package com.example.iddog.data

import com.example.iddog.data.api.FeedApiData
import com.example.iddog.model.Feed
import kotlin.reflect.KClass

object ApiData {

    fun <Entity : Any> of(clazz: KClass<*>): DataSource<Entity> {
        return when (clazz) {
            Feed::class -> FeedApiData()
            else -> throw IllegalArgumentException("Unsupported data type")
        } as DataSource<Entity>
    }
}