package com.gongshijie.feed

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.gongshijie.feed.api.NewsCell

class NoImageVH(view: View) : BaseViewHolder(view) {
    private val TAG = "NoImageVH"
    private val titleTextView: TextView = itemView.findViewById(R.id.title)
    private val extraTextView: TextView = itemView.findViewById(R.id.extra_info)


    override fun bindViewHolder(position: Int, newsCell: NewsCell, activity: FragmentActivity?) {
        titleTextView.text = newsCell.title
        extraTextView.text = newsCell.authorName
    }

    override fun onClickItem(itemView: View, cell: NewsCell) {
        Log.i(TAG, "onClickItem")
        ARouter.getInstance().build("/app/detail/DetailActivity")
            .withString("url", cell.url)
            .navigation();
    }
}
