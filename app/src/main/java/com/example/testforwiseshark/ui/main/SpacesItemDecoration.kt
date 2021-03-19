package com.example.testforwiseshark.ui.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(private val spaceInPixels: Int, private val spanCount: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view) // item position

        val column = position % spanCount // item column

        outRect.left =
            spaceInPixels - column * spaceInPixels / spanCount
        outRect.right =
            (column + 1) * spaceInPixels / spanCount
        if (position < spanCount) { // top edge
            outRect.top = spaceInPixels
        }
        outRect.bottom = spaceInPixels // item bottom

    }
}