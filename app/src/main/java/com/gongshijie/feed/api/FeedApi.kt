package com.gongshijie.feed.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

object FeedApi {

    lateinit var data: MutableLiveData<List<NewsCell>>
    private var okHttpClient = OkHttpClient()

    fun asyncFetchNewsCells(pageIndex: Int, pageCount: Int, type: String) {

        val newsCellList = mutableListOf<NewsCell>()
        val httpUrl = HttpUrl.Builder().host("v.juhe.cn")
            .scheme("https")
            .addPathSegment("toutiao")
            .addPathSegment("index")
            .addQueryParameter("type", type)
            .addQueryParameter("page", pageIndex.toString())
            .addQueryParameter("page_size", pageCount.toString())
            .addQueryParameter("key", "6351037e0245a94ecae0bd699f2d72f3").build()
        val request = Request.Builder().url(httpUrl).build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("FeedApi", "fail : ${e.toString()}")
            }

            override fun onResponse(call: Call, response: Response) {

                Log.i("FeedApi", "onResponse : ${response.body}")

                val responseData = response.body?.string()
                val json = JSONObject(responseData)
                val newsList = json.getJSONObject("result").getJSONArray("data")

                for (i in 0 until newsList.length()) {

                    val item = newsList.getJSONObject(i)
                    val uniqueKey = item.getString("uniquekey")
                    val title = item.getString("title")
                    val date = item.getString("date")
                    val category = item.getString("category")
                    val authorName = item.getString("author_name")
                    val url = item.getString("url")
                    val thumbnailPic = item.getString("thumbnail_pic_s")

                    val newsCell =
                        NewsCell(uniqueKey, title, date, category, authorName, url, thumbnailPic)

                    newsCellList.add(newsCell)


                }

                data.postValue(newsCellList)
            }
        })

    }

    fun asyncFetchNewsCells() = asyncFetchNewsCells(1, 20, "top")

    fun asyncFetchNewsCells(type: String) = asyncFetchNewsCells(1, 20, type)

}