package com.gongshijie.feed.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gongshijie.feed.api.NewsCell
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsCellDao {
    @Query("SELECT * FROM news where type = :type")
    fun getAll(type: String): List<NewsCell>?

    @Insert
    fun insertAll(newsCells: List<NewsCell>)

    @Delete
    fun delete(newsCell: NewsCell)

    @Query("DELETE FROM news")
    fun clearNews()
}