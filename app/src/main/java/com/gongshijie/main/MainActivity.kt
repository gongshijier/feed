package com.gongshijie.main

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.launcher.ARouter
import com.gongshijie.feed.BaseFragmentStateAdapter
import com.gongshijie.feed.R
import com.gongshijie.feed.api.CONSTANTS
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var fragmentAdapter: BaseFragmentStateAdapter
    lateinit var bottomNav: BottomNavigationView

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

        fragmentAdapter = BaseFragmentStateAdapter(this, CONSTANTS.newsTypeMap.size)

        viewPager.adapter = fragmentAdapter

        tabLayout = findViewById(R.id.tab_layout)
        bottomNav = findViewById(R.id.nav_view)

        val typeNews = CONSTANTS.newsTypeMap
        val tabText = ArrayList(typeNews.values)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabText[position].toString()
        }.attach()

        tabLayout.getTabAt(1)?.select()

        bottomNav.setOnNavigationItemSelectedListener { item ->
            val fragmentManager = supportFragmentManager
            when (item.itemId) {
                R.id.navigation_home -> {
                    viewPager.currentItem = 1
                }
                R.id.navigation_xigua -> {
                    viewpager.currentItem = 12
                }
                R.id.navigation_cinema -> {
                    ARouter.getInstance().build("/cinema/TKTKActivity").navigation()
                }
                R.id.navigation_me -> {
                    ARouter.getInstance().build("/me/MeActivity").navigation()
                }
            }
            true
        }
    }
}