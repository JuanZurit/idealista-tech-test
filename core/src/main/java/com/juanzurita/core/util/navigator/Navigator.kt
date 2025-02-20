package com.juanzurita.core.util.navigator

import android.app.Activity
import android.content.Context
import android.content.Intent

class Navigator(private val activity: Activity,private val context: Context) {

    fun navigate(intent: Intent) {
        return Navigation(activity, context,intent).navigate()
    }

}