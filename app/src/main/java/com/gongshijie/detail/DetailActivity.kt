package com.gongshijie.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.webkit.WebView
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gongshijie.feed.R
import com.gongshijie.feed.api.NewsCell
import com.gongshijie.feed.api.RecordEvent
import org.greenrobot.eventbus.EventBus

@Route(path = "/app/detail/DetailActivity")
class DetailActivity : AppCompatActivity() {
    lateinit var webView: WebView

    @Autowired(name = "url")
    lateinit var url: String

    @Autowired(name = "record")
    lateinit var record: String

    @Autowired(name = "position")
    lateinit var position: String
    private val TAG = "DetailActivity"


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.statusBarColor = 0xFFFFFF

        webView = findViewById(R.id.detail_webView)

        ARouter.getInstance().inject(this)

        webView.loadUrl(url)

        webView.postDelayed({
            webView.scrollBy(0, record.toFloat().toInt())
        }, 300)

        Log.i(TAG, "newsCell : url -> $url")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            webView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                record = webView.scrollY.toString()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        Log.i("gongshijie", "详情页 onResume----" + this.toString())
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().post(RecordEvent(position.toFloat().toInt(), record.toFloat().toInt()))
    }
}