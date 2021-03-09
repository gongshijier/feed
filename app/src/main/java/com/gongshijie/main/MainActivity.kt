package com.gongshijie.main

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.gongshijie.feed.BaseFragmentStateAdapter
import com.gongshijie.feed.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var fragmentAdapter: BaseFragmentStateAdapter

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.statusBarColor = 0xFFFFFF

        viewPager = findViewById(R.id.viewpager)

        fragmentAdapter = BaseFragmentStateAdapter(this, 10)

        viewPager.adapter = fragmentAdapter

        tabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            when (position) {
                0 -> {
                    tab.text = "关注"
                }

                1 -> {
                    tab.text = "推荐"
                }

                2 -> {
                    tab.text = "娱乐"
                }

                3 -> {
                    tab.text = "财经"
                }

                4 -> {
                    tab.text = "体育"
                }

                5 -> {
                    tab.text = "军事"
                }

                6 -> {
                    tab.text = "科技"
                }

                7 -> {
                    tab.text = "财经"
                }

                8 -> {
                    tab.text = "时尚"
                }

                9 -> {
                    tab.text = "游戏"
                }

                10 -> {
                    tab.text = "汽车"
                }

                11 -> {
                    tab.text = "健康"
                }
            }
        }.attach()

        tabLayout.getTabAt(1)?.select()

    }
}