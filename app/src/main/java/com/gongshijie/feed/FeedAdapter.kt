package com.gongshijie.feed

import android.view.ViewGroup
import androidx.core.os.TraceCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.gongshijie.feed.api.NewsCell

open class FeedAdapter(
    open var dataList: List<NewsCell>?,
    private val activity: FragmentActivity?
) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return VHManager.createVH(parent, viewType)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        TraceCompat.beginSection("RV onBindViewHolder")
        holder.itemView.setOnClickListener {
            dataList?.get(position)?.let { it1 -> holder.onClickItem(it, it1) }
        }
        dataList?.get(position)?.let { holder.bindViewHolder(position, it, activity) }
        TraceCompat.endSection()
    }

    override fun getItemViewType(position: Int): Int {
        if (position < 3) return VHType.NO_IMAGE.type
        if (position % 3 == 0) return VHType.BIG_IMAGE.type
        if(position % 4 == 0) return VHType.XIGUA.type
        return dataList?.get(position)?.let { VHParser.parseViewType(it) } ?: 0
    }

}