package com.gongshijie.feed

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class FeedRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var startX = 0
    var startY = 0

    override fun onTouchEvent(e: MotionEvent?): Boolean {

        val action = e?.actionMasked
        val actionIndex = e?.actionIndex

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                startX = e.x.toInt()
                startY = e.y.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                val endX = e.x.toInt()
                val endY = e.y.toInt()
                val disX = Math.abs(endX - startX)
                val disY = Math.abs(endY - startY)

//                if (disY > disX) {
//                    requestDisallowInterceptTouchEvent(false)
//                }
            }


        }

        return super.onTouchEvent(e)
    }

}
