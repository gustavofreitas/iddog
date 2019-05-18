package com.example.iddog.data.entity

import androidx.room.Entity

@Entity(tableName = "feed", primaryKeys = arrayOf("categoty", "url"))
data class FeedEntity(var categoty: String,
                      var url: String)