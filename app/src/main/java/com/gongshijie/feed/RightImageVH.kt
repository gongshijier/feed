package com.gongshijie.feed

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.gongshijie.feed.api.NewsCell


class RightImageVH(itemView: View) : BaseViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)
    private val extraInfo: TextView = itemView.findViewById(R.id.extra_info)
    private val TAG = "RightImageVH"

    override fun bindViewHolder(position: Int, newsCell: NewsCell, activity: FragmentActivity?) {
        textView.text = newsCell.title
        extraInfo.text = newsCell.authorName
        clickPosition = position
        activity?.let { bindImage(newsCell, imageView, it) }
    }

    private fun bindImage(newsCell: NewsCell, imageView: ImageView, context: Context) {
        Glide.with(context)
            .load(newsCell.thumbnailPic)
            .into(imageView)
    }

    override fun onClickItem(itemView: View, cell: NewsCell) {
        Log.i(TAG, "onClickItem")
        ARouter.getInstance().build("/app/detail/DetailActivity")
            .withString("url", cell.url)
            .withString("record", cell.record.toString())
            .withString("position", ""+clickPosition)
            .navigation()
    }
}
