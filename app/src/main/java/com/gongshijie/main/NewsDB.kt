package com.gongshijie.main

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gongshijie.feed.api.NewsCell
import com.gongshijie.feed.db.NewsCellDao

@Database(entities = [NewsCell::class], version = 2)
abstract class NewsDB : RoomDatabase() {
    abstract fun newsDao(): NewsCellDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDB? = null

        fun getDatabase(
            context: Context
        ): NewsDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDB::class.java,
                    "news_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}
