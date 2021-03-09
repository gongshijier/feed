package com.gongshijie.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.TraceCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gongshijie.feed.api.NewsCell

class FeedAdapter(
    var dataList: List<NewsCell>?,
    val activity: FragmentActivity?
) :
    RecyclerView.Adapter<FeedAdapter.BaseViewHolder>() {


    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bindViewHolder(position: Int, newsCell: NewsCell, activity: FragmentActivity?) {
            textView.text = newsCell.title
            activity?.let { bindImage(newsCell, imageView, it) }
        }

        private fun bindImage(newsCell: NewsCell, imageView: ImageView, context: Context) {

            Glide.with(context)
                .load(newsCell.thumbnailPic)
                .into(imageView)
//            when ((System.currentTimeMillis() % 10).toInt()) {
//                0 -> imageView.setImageResource(R.drawable.ic_car)
//                1 -> imageView.setImageResource(R.drawable.ic_box)
//                2 -> imageView.setImageResource(R.drawable.ic_charge)
//                3 -> imageView.setImageResource(R.drawable.ic_light)
//                4 -> imageView.setImageResource(R.drawable.ic_mp3)
//                5 -> imageView.setImageResource(R.drawable.ic_nian)
//                6 -> imageView.setImageResource(R.drawable.ic_radio)
//                7 -> imageView.setImageResource(R.drawable.ic_rocket)
//                8 -> imageView.setImageResource(R.drawable.ic_round)
//                9 -> imageView.setImageResource(R.drawable.ic_sheep)
//
//            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        TraceCompat.beginSection("RV onCreateViewHolder")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main, parent, false)
        TraceCompat.endSection()
        return BaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        TraceCompat.beginSection("RV onBindViewHolder")
        dataList?.get(position)?.let { holder.bindViewHolder(position, it, activity) }
        TraceCompat.endSection()
    }


}