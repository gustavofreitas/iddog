package com.example.iddog.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.iddog.data.db.FeedDao
import com.example.iddog.data.entity.FeedEntity

@Database(entities = [FeedEntity::class],
    version = 2,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getFeedDao(): FeedDao

    companion object{
        private const val DB_NAME = "IdDogCache.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context.applicationContext).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
