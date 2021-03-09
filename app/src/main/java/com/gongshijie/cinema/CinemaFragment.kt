package com.gongshijie.cinema

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gongshijie.feed.R


class CinemaFragment : Fragment() {
    private lateinit var cinemaViewModel: CinemaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        cinemaViewModel = ViewModelProvider(this).get(CinemaViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_cinema, container, false)

        return root
    }
}