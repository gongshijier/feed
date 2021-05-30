package com.gongshijie.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.widget.EditText
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.gongshijie.feed.R
import com.gongshijie.xigua.baidu
import kotlinx.android.synthetic.main.activity_detail.*

@Route(path = "/search/SearchActivity")
class SearchActivity : AppCompatActivity() {
    lateinit var searchBtn: ImageView
    lateinit var searchResult: WebView
    lateinit var searchEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.statusBarColor = 0xFFFFFF

        searchBtn = findViewById(R.id.search_btn)
        searchResult = findViewById(R.id.search_list)
        searchEdit = findViewById(R.id.search_edit)

        searchResult.settings.javaScriptEnabled = true
        searchBtn.setOnClickListener({
            // 点击进行搜索
            searchResult.loadUrl(baidu + searchEdit.text)
        })

        searchEdit.setOnEditorActionListener({ v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // 回车进行搜索
                searchResult.loadUrl(baidu + searchEdit.text)
            }
            return@setOnEditorActionListener false
        })
    }
}