package com.sanmidev.mybakingapp.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.facebook.shimmer.ShimmerFrameLayout

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}


fun fireToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


inline fun View.showIf(condition: (View) -> Boolean) {
    if (condition(this)) {
        visible()
    } else {
        gone()
    }
}

inline fun ShimmerFrameLayout.showShimmerIf(condition: (View) -> Boolean) {
    if (condition(this)) {
        startShimmer()
    } else {
        stopShimmer()
        gone()
    }
}
