package com.gongshijie.feed.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userInfo")
data class UserInfo(
    @PrimaryKey var uniqueKey: String,
    @ColumnInfo(name = "likesNewsKey") var likesNewsKey: String,
    @ColumnInfo(name = "city") var city: String,
    @ColumnInfo(name = "lastRefreshTime") var lastRefreshTime: String,
    @ColumnInfo(name = "dislikeNewsKey") var dislikeNewsKey: String
) {
}