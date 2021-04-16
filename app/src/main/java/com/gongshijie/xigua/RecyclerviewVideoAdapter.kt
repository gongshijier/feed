package com.gongshijie.xigua

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.bumptech.glide.Glide
import com.gongshijie.feed.R

class RecyclerviewVideoAdapter(
    val context: Context?,
    private var videoList: ArrayList<VideoCell>
) : RecyclerView.Adapter<RecyclerviewVideoAdapter.VideoVH>() {


    class VideoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var jzvdStd: JzvdStd = itemView.findViewById(R.id.videoplayer)
        var titleView: TextView = itemView.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoVH {
        return VideoVH(LayoutInflater.from(context).inflate(R.layout.item_videoview, parent, false))
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoVH, position: Int) {
        var videocell: VideoCell = videoList[position]
        holder.jzvdStd.setUp(videocell.url, "", Jzvd.SCREEN_NORMAL)
        holder.titleView.text = "听，清明节里的最强音 - 新华网-吉林频道"
        Glide.with(holder.jzvdStd.context).load(videocell.thumb).into(holder.jzvdStd.posterImageView)
    }

}
