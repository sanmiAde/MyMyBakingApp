package com.sanmidev.mybakingapp.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.appbar.MaterialToolbar
import com.sanmidev.mybakingapp.R
import com.sanmidev.mybakingapp.feature.MainActivity

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

/***
 * Iniialises the toolbar button with a black icon
 * @param activity is the [MainActivity]
 * @param toolbar is the toolbar to be initialised
 * @author oluwasanmi Aderibigbe
 */
fun initToolbarButton(activity: Activity, toolbar: MaterialToolbar) {
    toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
    toolbar.setNavigationOnClickListener {
        (activity as MainActivity).onBackPressed()
    }
}
