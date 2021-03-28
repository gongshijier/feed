package com.gongshijie.feed.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsCell(
    @PrimaryKey var uniqueKey: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "category") var category: String,
    @ColumnInfo(name = "authorName") var authorName: String,
    @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "thumbnailPic") var thumbnailPic: String,
    @ColumnInfo(name = "type") var type: String
) {

}