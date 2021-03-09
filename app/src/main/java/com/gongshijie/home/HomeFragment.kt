package com.gongshijie.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gongshijie.feed.FeedAdapter
import com.gongshijie.feed.R
import com.gongshijie.feed.api.FeedApi
import com.gongshijie.feed.api.NewsCell


class HomeFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    var dataList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)

    var loadStatus = MutableLiveData<Int>()
    lateinit var adapter: FeedAdapter
    private lateinit var homeViewModel: HomeViewModel



    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)



        FeedApi.data = homeViewModel.data
        FeedApi.asyncFetchNewsCells()

        adapter = FeedAdapter(
            homeViewModel.data.value,
            this.activity
        )

        recyclerView = root.findViewById(R.id.feed_recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        val itemDividerItemDecoration =
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDividerItemDecoration)

        homeViewModel.data.observe(viewLifecycleOwner, object : Observer<List<NewsCell>> {
            override fun onChanged(t: List<NewsCell>?) {
                adapter.dataList = t
                adapter.notifyDataSetChanged()
            }
        })

        return root

    }


}