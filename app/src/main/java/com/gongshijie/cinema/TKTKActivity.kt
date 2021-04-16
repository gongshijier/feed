package com.gongshijie.cinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.get
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.Jzvd
import com.alibaba.android.arouter.facade.annotation.Route
import com.gongshijie.feed.R

@Route(path = "/cinema/TKTKActivity")
class TKTKActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var adapter: TKTKAdapter
    lateinit var viewpagerLM: ViewPagerLayoutManager
    var mCurrentPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tktk)

        recyclerView = findViewById(R.id.rv_tiktok)
        adapter = TKTKAdapter(this)
        viewpagerLM = ViewPagerLayoutManager(this, OrientationHelper.VERTICAL)
        recyclerView.layoutManager = viewpagerLM
        recyclerView.adapter = adapter


        viewpagerLM.setOnViewPagerListener(object: OnViewPagerListener{
            override fun onInitComplete() {
                autoPlayVideo(0)
            }

            override fun onPageRelease(isNext: Boolean, position: Int) {
                if (mCurrentPosition == position)
                    Jzvd.releaseAllVideos()
            }

            override fun onPageSelected(position: Int, isBottom: Boolean) {
                if (position != mCurrentPosition) {
                    autoPlayVideo(position)
                    mCurrentPosition = position
                }
            }

        })

        recyclerView.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener{
            override fun onChildViewDetachedFromWindow(view: View) {
                var jzvd: Jzvd = view.findViewById(R.id.videoplayer)
                if (jzvd != null && Jzvd.CURRENT_JZVD != null && jzvd.jzDataSource != null&& jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.currentUrl)) {
                    if (Jzvd.CURRENT_JZVD != null && Jzvd.CURRENT_JZVD.screen != Jzvd.SCREEN_FULLSCREEN) {
                        Jzvd.releaseAllVideos()
                    }
                }
            }

            override fun onChildViewAttachedToWindow(view: View) {

            }

        })
    }

    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()
    }

    private fun autoPlayVideo(postion: Int) {
        if (recyclerView.getChildAt(0) == null) {
            return
        }
        val player: JzvdStdTikTok = recyclerView.getChildAt(0).findViewById(R.id.videoplayer)
        player?.startVideoAfterPreloading()
    }
}