package com.gongshijie.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.TraceCompat

object VHManager {
    fun createVH(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            VHType.RIGHT_IMAGE.type -> {
                TraceCompat.beginSection("RV onCreateViewHolder")
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.vh_right_image, parent, false)
                TraceCompat.endSection()
                return RightImageVH(view)
            }
            VHType.NO_IMAGE.type -> {
                TraceCompat.beginSection("RV onCreateViewHolder")
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.vh_no_image, parent, false)
                TraceCompat.endSection()
                return NoImageVH(view)
            }
            VHType.BIG_IMAGE.type -> {
                TraceCompat.beginSection("RV onCreateViewHolder")
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.vh_big_image, parent, false)
                TraceCompat.endSection()
                return BigImageVH(view)
            }
            VHType.XIGUA.type -> {
                TraceCompat.beginSection("RV onCreateViewHolder")
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.vh_xigua_image, parent, false)
                TraceCompat.endSection()
                return XiguaVH(view)
            }
        }
        TraceCompat.beginSection("RV onCreateViewHolder")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vh_right_image, parent, false)
        TraceCompat.endSection()
        return RightImageVH(view)
    }
}
