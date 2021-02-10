package com.gongshijie.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

class FeedAdapter(private val dataList: MutableLiveData<List<Int>>) :
    RecyclerView.Adapter<FeedAdapter.BaseViewHolder>() {


    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bindViewHolder(position: Int) {
            textView.text = "$position"
            bindImage(imageView)
        }

        private fun bindImage(imageView: ImageView) {

            when ((System.currentTimeMillis() % 10).toInt()) {
                0 -> imageView.setImageResource(R.drawable.ic_car)
                1 -> imageView.setImageResource(R.drawable.ic_box)
                2 -> imageView.setImageResource(R.drawable.ic_charge)
                3 -> imageView.setImageResource(R.drawable.ic_light)
                4 -> imageView.setImageResource(R.drawable.ic_mp3)
                5 -> imageView.setImageResource(R.drawable.ic_nian)
                6 -> imageView.setImageResource(R.drawable.ic_radio)
                7 -> imageView.setImageResource(R.drawable.ic_rocket)
                8 -> imageView.setImageResource(R.drawable.ic_round)
                9 -> imageView.setImageResource(R.drawable.ic_sheep)

            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main, parent, false)
        return BaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.value?.size ?: 0
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindViewHolder(position)
    }


}