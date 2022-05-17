package com.varani.gtmotors.utils

import android.view.View

val Any.TAG: String
    get() = javaClass.simpleName

fun View.show(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}