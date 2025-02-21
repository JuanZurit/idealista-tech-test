package com.juanzurita.core.util.extensions

import android.view.View

// ////////////////////////////////////////////////////////////////////////
//
//                              VIEWS
//
// ////////////////////////////////////////////////////////////////////////

fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}

fun View?.visible(show: Boolean = true) {
    this?.visibility = if (show) View.VISIBLE else View.INVISIBLE
}


fun <T : View> T?.gone(gone: Boolean = true): T? {
    if (gone) {
        this?.visibility = View.GONE
    } else {
        this?.visibility = View.VISIBLE
    }
    return this
}