package com.android.springanimation

import android.view.View
import android.view.ViewTreeObserver

inline fun <T : View> T.onLayoutReady(crossinline callBackFunction: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            callBackFunction()
        }
    })
}