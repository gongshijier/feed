package com.gongshijie.cinema

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JZDataSource
import cn.jzvd.Jzvd
import com.bumptech.glide.Glide
import com.gongshijie.feed.R
import com.gongshijie.xigua.pl3
import com.gongshijie.xigua.tl3
import com.gongshijie.xigua.vl3

class TKTKAdapter(val context: Context) : RecyclerView.Adapter<TKTKAdapter.TKTKVH>() {

    var videoIndexs = intArrayOf(0, 1, 2, 3, 4, 5)

    class TKTKVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var jzvdStd: JzvdStdTikTok = itemView.findViewById(R.id.videoplayer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TKTKVH {
        return TKTKVH(LayoutInflater.from(context).inflate(R.layout.item_tiktok, parent, false))
    }

    override fun getItemCount(): Int {
        return videoIndexs.size
    }

    override fun onBindViewHolder(holder: TKTKVH, position: Int) {
        var jzDataSource = JZDataSource(vl3[position], tl3[position])
        jzDataSource.looping = true
        holder.jzvdStd.setUp(jzDataSource, Jzvd.SCREEN_NORMAL)
        Glide.with(holder.jzvdStd.context).load(pl3[position]).into(holder.jzvdStd.posterImageView)
    }

}
