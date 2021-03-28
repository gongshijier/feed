package com.gongshijie.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gongshijie.feed.api.NewsCell
import com.gongshijie.feed.db.NewsCellDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class NewsRepository(var newsDao: NewsCellDao) {

    lateinit var viewModel: HomeViewModel
    lateinit var data: MutableLiveData<List<NewsCell>>
    private var okHttpClient = OkHttpClient()
    private val TAG = "NewsRepository"


    suspend fun asyncFetchNewsNet(pageIndex: Int, pageCount: Int, type: String) {
        withContext(Dispatchers.IO) {
            Log.i(
                TAG,
                "asyncFetchNewsNet------------> Thread------------->${Thread.currentThread().name}"
            )

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
                    if (json.optInt("resultcode") == 112) return // No Data !

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
                            NewsCell(
                                uniqueKey,
                                title,
                                date,
                                category,
                                authorName,
                                url,
                                thumbnailPic,
                                type
                            )

                        newsCellList.add(newsCell)

                    }

                    data.postValue(newsCellList)

                    runBlocking {
                        withContext(Dispatchers.IO) {
                            viewModel.newsRepository.newsDao.clearNews()
                            viewModel.saveNews(newsCellList)
                        }
                    }
                }
            })
        }
    }

    suspend fun saveNews(list: List<NewsCell>) {
        withContext(Dispatchers.IO) {
            Log.i(
                TAG,
                "saveNews------------> Thread------------->${Thread.currentThread().name}"
            )
            newsDao.insertAll(list)
        }
    }

    suspend fun asyncFetchNewsDB(type: String) {
        withContext(Dispatchers.IO) {
            Log.i(
                TAG,
                "asyncFetchNewsDB------------> Thread------------->${Thread.currentThread().name}"
            )
            val result = newsDao.getAll(type)
            result?.let {
                if (result.isNotEmpty())
                    data.postValue(it)
            }
        }
    }
}



