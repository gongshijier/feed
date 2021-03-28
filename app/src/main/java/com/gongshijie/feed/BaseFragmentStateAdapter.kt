package com.gongshijie.feed

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.gongshijie.feed.api.CONSTANTS
import com.gongshijie.home.HomeFragment
import com.gongshijie.xigua.XiguaFragment

class BaseFragmentStateAdapter(activity: AppCompatActivity, val itemsCount: Int) :
    FragmentStateAdapter(activity) {


    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        if (position == CONSTANTS.newsTypeMap.size - 1) {
            return XiguaFragment()
        }
        val homeFragment = HomeFragment()
        homeFragment.position = position
        return homeFragment

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(observer)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun containsItem(itemId: Long): Boolean {
        return super.containsItem(itemId)
    }
}
