package com.example.iddog.data.entity

import androidx.room.Entity

@Entity(tableName = "feed", primaryKeys = ["category", "url"])
data class FeedEntity(var category: String,
                      var url: String)