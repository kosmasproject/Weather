package com.kosmasfn.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Kosmas on October 11, 2023
 */
fun RecyclerView.setEndlessScrollListener(
    block: () -> Unit
) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        var loading = true

        override fun onScrollStateChanged(
            recyclerView: RecyclerView,
            newState: Int
        ) {
            if (canScrollVertically(-1)) {
                with(layoutManager as LinearLayoutManager) {
                    val visibleItemCount = childCount
                    val totalItemCount = itemCount
                    val firstVisibleItem = findFirstVisibleItemPosition()

                    if (loading) {
                        if ((visibleItemCount + firstVisibleItem) >= totalItemCount) {
                            loading = false;

                            block()

                            loading = true;
                        }
                    }
                }
            }
        }
    })
}