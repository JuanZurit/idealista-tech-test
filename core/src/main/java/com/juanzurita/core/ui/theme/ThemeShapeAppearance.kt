package com.juanzurita.core.ui.theme

import androidx.annotation.AttrRes
import com.juanzurita.core.R

sealed class ThemeShapeAppearance(@AttrRes val value: Int) {
    data object None : ThemeShapeAppearance(R.attr.shapeAppearanceNone)
    data object Full :
        ThemeShapeAppearance(com.google.android.material.R.attr.shapeAppearanceSmallComponent)

    data object SmallComponentBottom : ThemeShapeAppearance(R.attr.shapeAppearanceSmallComponentBottom)
    data object SmallComponentBottomLeft :
        ThemeShapeAppearance(R.attr.shapeAppearanceSmallComponentBottomLeft)

    data object SmallComponentBottomRight :
        ThemeShapeAppearance(R.attr.shapeAppearanceSmallComponentBottomRight)

    data object SmallComponentTop : ThemeShapeAppearance(R.attr.shapeAppearanceSmallComponentTop)
}
