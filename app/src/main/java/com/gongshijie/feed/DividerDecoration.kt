package com.gongshijie.feed

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DividerDecoration(context: Context?, vertical: Int) : RecyclerView.ItemDecoration() {
    private val mDividerSize = 0.8
    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.apply {
            color = Color.parseColor("#ffd8d8d8")
            style = Paint.Style.FILL
        }
    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left: Int = parent.paddingLeft + 10
        val right: Int = parent.measuredWidth - parent.paddingRight - 10
        val childSize = parent.childCount

        for (i in 0 until childSize) {
            val child = parent.getChildAt(i)
            if (parent.getChildAdapterPosition(child) in 0 .. 1) continue
            val layoutParams =
                child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + layoutParams.bottomMargin + 20

            val bottom = (top + mDividerSize)
            //绘制矩形
            c.drawRect(
                left.toFloat(),
                top.toFloat(),
                right.toFloat(),
                bottom.toFloat(),
                mPaint
            )

        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val viewPos = parent.getChildAdapterPosition(view)

        if (viewPos in 0..1) {
            outRect.set(0, 0, 0, 0)
            return
        }


        outRect.set(0, 0, 0, 1)
    }
}
