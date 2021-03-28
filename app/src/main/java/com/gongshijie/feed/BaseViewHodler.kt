package com.gongshijie.feed

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.gongshijie.feed.api.NewsCell

open class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    open fun bindViewHolder(position: Int, newsCell: NewsCell, activity: FragmentActivity?) {}
    open fun onClickItem(itemView: View, cell: NewsCell) {}


}
