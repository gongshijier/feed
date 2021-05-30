package com.gongshijie.feed.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gongshijie.feed.api.NewsCell
import com.gongshijie.feed.api.UserInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInfoDao {

    @Delete
    fun delete(user: UserInfo)

    @Query("DELETE FROM userInfo")
    fun clearUsers()
}