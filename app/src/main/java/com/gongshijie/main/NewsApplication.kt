package com.gongshijie.main

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.gongshijie.home.NewsRepository

class NewsApplication : Application() {

    public val newsDB by lazy { NewsDB.getDatabase(this) }

    public val newsRepository by lazy { NewsRepository(newsDB.newsDao()) }

    override fun onCreate() {
        super.onCreate()
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
    }



}