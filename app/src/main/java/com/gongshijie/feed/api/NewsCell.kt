package com.gongshijie.feed.api

data class NewsCell(
    var uniqueKey: String,
    var title: String,
    var date: String,
    var category: String,
    var authorName: String,
    var url: String,
    var thumbnailPic: String
) {

}