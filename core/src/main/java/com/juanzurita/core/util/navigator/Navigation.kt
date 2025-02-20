package com.juanzurita.core.util.navigator

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.AnimRes
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair


class Navigation internal constructor(private val activity: Activity,private val context: Context,private val intent: Intent) {

    private var options: ActivityOptionsCompat? = null
    private var animationPair: Pair<Int, Int>? = null
    private var requestCode = 0
    private var forResult = false
    private var resultLauncher: ActivityResultLauncher<Intent>? = null

    fun navigate() {
        val activities = activity.packageManager.queryIntentActivities(intent, 0)
        if (activities.isEmpty()) return
        if (forResult) {
            if (resultLauncher != null)
                resultLauncher?.launch(intent, options)
            else
                ActivityCompat.startActivityForResult(activity, intent, requestCode, options?.toBundle())
        } else {
            context.startActivity(intent, options?.toBundle())
        }
    }

    fun forResult(resultLauncher: ActivityResultLauncher<Intent>): Navigation {
        this.resultLauncher = resultLauncher
        this.forResult = true
        return this
    }


    fun forResult(requestCode: Int): Navigation {
        forResult = true
        this.requestCode = requestCode
        return this
    }

    fun clearBackStack(): Navigation {
        intent.flags = intent.flags or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        return this
    }

    fun singleTop(): Navigation {
        intent.flags = intent.flags or Intent.FLAG_ACTIVITY_SINGLE_TOP
        return this
    }

    fun clearTop(): Navigation {
        intent.flags = intent.flags or Intent.FLAG_ACTIVITY_CLEAR_TOP
        return this
    }

    fun noHistory(): Navigation {
        intent.flags = intent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
        return this
    }

    fun withAnimation(@AnimRes enterAnim: Int, @AnimRes exitAnim: Int): Navigation {
        animationPair = Pair.create(enterAnim, exitAnim)
        return this
    }

    fun withTransition(options: ActivityOptionsCompat?): Navigation {
        this.options = options
        return this
    }

}