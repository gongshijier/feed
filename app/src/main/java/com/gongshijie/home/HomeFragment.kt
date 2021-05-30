package com.gongshijie.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.bumptech.glide.Glide
import com.gongshijie.feed.DividerDecoration
import com.gongshijie.feed.FeedAdapter
import com.gongshijie.feed.FeedRecyclerView
import com.gongshijie.feed.R
import com.gongshijie.feed.api.CONSTANTS
import com.gongshijie.feed.api.NewsCell
import com.gongshijie.main.NewsApplication
import kotlinx.coroutines.launch
import java.io.FileReader


class HomeFragment : Fragment(), BGARefreshLayout.BGARefreshLayoutDelegate {
    var THRESHOLD_PAUSE = 200
    var THRESHOLD_RESUME = 100

    var position: Int = 0
    private var pageIndex = 1
    lateinit var recyclerView: FeedRecyclerView

    val typeNews = CONSTANTS.newsTypeMap

    val keys = ArrayList(typeNews.keys)

    var loadStatus = MutableLiveData<Int>()
    lateinit var adapter: FeedAdapter
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var refreshLayout: BGARefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.newsRepository = (activity?.application as NewsApplication).newsRepository

        homeViewModel.newsRepository.data = homeViewModel.data

        homeViewModel.newsRepository.viewModel = homeViewModel

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        lifecycleScope.launch {
            keys[position]?.let { homeViewModel.asyncFetch(it) }
        }


        adapter = FeedAdapter(
            homeViewModel.data.value,
            this.activity
        )

        recyclerView = root.findViewById(R.id.feed_recyclerView)
        refreshLayout = root.findViewById(R.id.refresh_feed)
        refreshLayout.setDelegate(this)
        val refreshViewHolder = BGANormalRefreshViewHolder(context, true)

        refreshViewHolder.apply {
            setRefreshViewBackgroundColorRes(R.color.color_F3F5F4)
            setPullDownRefreshText("下拉推荐")
            setRefreshingText("推荐中")
        }

        refreshLayout.setRefreshViewHolder(refreshViewHolder)
        refreshLayout.shouldHandleRecyclerViewLoadingMore(recyclerView)


        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        val itemDividerItemDecoration =
            DividerDecoration(context, DividerItemDecoration.VERTICAL)

        recyclerView.addItemDecoration(itemDividerItemDecoration)

        homeViewModel.data.observe(viewLifecycleOwner, object : Observer<List<NewsCell>> {
            override fun onChanged(t: List<NewsCell>?) {
                adapter.dataList = t
                adapter.notifyDataSetChanged()
                if (refreshLayout.isLoadingMore) refreshLayout.endLoadingMore()
                if (refreshLayout.currentRefreshStatus == BGARefreshLayout.RefreshStatus.REFRESHING) refreshLayout.endRefreshing()

            }
        })

        return root

    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {

        recyclerView.postDelayed({
            refreshLayout?.endLoadingMore()
        }, 2500)
        return true
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {

        pageIndex += 1
        homeViewModel.asyncFetchNewsNet(pageIndex, 20, keys[position])
        recyclerView.postDelayed({
            refreshLayout?.endRefreshing()
        }, 2500)

    }

    override fun onResume() {
        super.onResume()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > THRESHOLD_PAUSE) {
                    context?.let { Glide.with(it).pauseRequests() }
                }

                if (dy < THRESHOLD_RESUME) {
                    context?.let { Glide.with(it).resumeRequests() }
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    context?.let {
                        if (Glide.with(it).isPaused) {
                            Glide.with(it).resumeRequests()
                        }
                    }
                }

            }
        })
    }

    override fun onStart() {
        super.onStart()
        adapter.onStart()
    }

    override fun onStop() {
        super.onStop()
        adapter.onStop()
    }
}