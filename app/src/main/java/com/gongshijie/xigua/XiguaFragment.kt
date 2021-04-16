package com.gongshijie.xigua

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.gongshijie.feed.R
import java.util.*
import kotlin.collections.ArrayList

@Route(path = "/xigua/XiguaFragment")
class XiguaFragment : Fragment() {

    private lateinit var xiguaViewModel: XiguaViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerviewVideoAdapter
    private var videoList = ArrayList<VideoCell>()


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        xiguaViewModel =
            ViewModelProvider(this).get(XiguaViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_xigua, container, false)

        recyclerView = root.findViewById(R.id.video_recyclerview)
        addVideo(videoList)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        adapter = RecyclerviewVideoAdapter(context, videoList)
        recyclerView.adapter = adapter
        return root
    }

    private fun addVideo(videoList: ArrayList<VideoCell>) {
        for (i in vl2.indices) {
            val videocell = VideoCell(vl2[i], tl2[i], pl2[i])
            videoList.add(videocell)
        }
        //随机打乱
        videoList.shuffle()
    }


}