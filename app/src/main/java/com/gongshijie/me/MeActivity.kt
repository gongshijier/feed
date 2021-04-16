package com.gongshijie.me

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.gongshijie.feed.DividerDecoration
import com.gongshijie.feed.R
import com.gongshijie.feed.api.CONSTANTS
import com.gongshijie.feed.api.NewsCell
import com.gongshijie.home.HomeViewModel
import com.gongshijie.main.NewsApplication
import kotlinx.coroutines.launch

@Route(path = "/me/MeActivity")
class MeActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FeedTodayAdapter
    lateinit var meViewmodel: HomeViewModel
    val typeNews = CONSTANTS.newsTypeMap
    val keys = ArrayList(typeNews.keys)

    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_me)

        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.statusBarColor = 0xFFFFFF

        recyclerView = findViewById(R.id.today_recyclerview)

        meViewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)

        meViewmodel.newsRepository = (this.application as NewsApplication).newsRepository


        lifecycleScope.launch {
            keys[0]?.let { meViewmodel.asyncFetch(it) }
        }

        meViewmodel.data = meViewmodel.newsRepository.data
        adapter = FeedTodayAdapter(meViewmodel.data.value, this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val itemDividerItemDecoration =
            DividerDecoration(this, DividerItemDecoration.VERTICAL)

        recyclerView.addItemDecoration(itemDividerItemDecoration)

        meViewmodel.data.observe(this,
            Observer<List<NewsCell>> { t ->
                adapter.dataList = t
                adapter.notifyDataSetChanged()
            })

    }
}