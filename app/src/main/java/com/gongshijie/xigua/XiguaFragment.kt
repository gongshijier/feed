package com.gongshijie.xigua

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.gongshijie.feed.R

@Route(path = "/xigua/XiguaFragment")
class XiguaFragment : Fragment() {

    private lateinit var xiguaViewModel: XiguaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        xiguaViewModel =
            ViewModelProvider(this).get(XiguaViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_xigua, container, false)


        return root
    }
}