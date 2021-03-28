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

class BigImageVH(view: View) : BaseViewHolder(view) {
    private val TAG = "BigImageVH"
    private val titleTextView: TextView = itemView.findViewById(R.id.title)
    private val extraTextView: TextView = itemView.findViewById(R.id.extra_info)
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    override fun bindViewHolder(position: Int, newsCell: NewsCell, activity: FragmentActivity?) {
        titleTextView.text = newsCell.title
        extraTextView.text = newsCell.authorName
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
            .navigation();
    }
}
