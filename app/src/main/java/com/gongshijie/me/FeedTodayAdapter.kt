package com.gongshijie.me

import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.gongshijie.feed.BaseViewHolder
import com.gongshijie.feed.FeedAdapter
import com.gongshijie.feed.VHType
import com.gongshijie.feed.api.NewsCell

class FeedTodayAdapter(
    override var dataList: List<NewsCell>?,
    private val activity: FragmentActivity?
) : FeedAdapter(dataList, activity) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        return VHType.NO_IMAGE.type
    }
}
