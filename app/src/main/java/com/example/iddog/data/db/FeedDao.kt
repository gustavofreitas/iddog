package com.example.iddog.data.db

import androidx.room.*
import com.example.iddog.data.entity.FeedEntity

@Dao
abstract class FeedDao{

    @Query("SELECT * FROM feed WHERE categoty = :category")
    abstract fun getAll(category: String): List<FeedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(users: List<FeedEntity>)

    @Delete
    abstract fun deleteAll(users: List<FeedEntity>)


}