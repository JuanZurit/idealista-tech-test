package com.juanzurita.presentation.base

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.juanzurita.core.util.navigator.Navigator
import com.juanzurita.presentation.R

open class BaseActivity:AppCompatActivity() {

    open lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        navigator = Navigator(this, this)
    }

    fun adjustInsetEdgeToEdge(
        v: View,
        top: Boolean = false,
        bottom: Boolean = false,
        left: Boolean = false,
        right: Boolean = false,
        forceLandScapeBackground: Boolean = false,
    ) {
        v.apply {
            ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
                val bars = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                            or WindowInsetsCompat.Type.displayCutout()
                )

                val isLandScape =
                    resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

                if (isLandScape && left && right && (forceLandScapeBackground || (!top && !bottom))) {
                    v.setBackgroundColor(resources.getColor(R.color.black, context.theme))
                }

                v.updatePadding(
                    left = if (left) bars.left else v.paddingLeft,
                    right = if (right) bars.right else v.paddingRight,
                    bottom = if (bottom) bars.bottom else v.paddingBottom,
                    top = if (top) bars.top else v.paddingTop
                )

                // Devuelve los insets sin consumirlos completamente
                return@setOnApplyWindowInsetsListener insets
            }
        }
    }
}