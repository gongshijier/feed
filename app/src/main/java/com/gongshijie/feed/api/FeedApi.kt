package com.gongshijie.feed.api

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

object CONSTANTS {

    lateinit var data: MutableLiveData<List<NewsCell>>
    private var okHttpClient = OkHttpClient()
    val newsTypeMap = linkedMapOf(
        Pair("top", "国内"),
        Pair("guonei", "推荐"),
        Pair("guoji", "国际"),
        Pair("yule", "娱乐"),
        Pair("tiyu", "体育"),
        Pair("junshi", "军事"),
        Pair("keji", "科技"),
        Pair("caijing", "财经"),
        Pair("shishang", "时尚"),
        Pair("youxi", "游戏"),
        Pair("qiche", "汽车"),
        Pair("jiankang", "健康"),
        Pair("xigua", "西瓜")
    )

    val todayAPI = "http://v.juhe.cn/todayOnhistory/queryEvent.php?key=aa5e60f2994b4ac0158bb6d1cadeef75&date=1/1"

}