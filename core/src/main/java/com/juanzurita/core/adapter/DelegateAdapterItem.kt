package com.juanzurita.core.adapter

import com.juanzurita.core.ui.theme.ThemeShapeAppearance
import com.juanzurita.core.util.Constants

abstract class DelegateAdapterItem(
    open var cellType: Int = Constants.CellType.MIDDEL,
    open var typeItem: Int = 0,
) {

    abstract fun id(): Any

    abstract fun content(): Any

    open fun payload(other: Any): Payloadable = Payloadable.None

    abstract fun copy(): DelegateAdapterItem

    /**
     * Simple marker interface for payloads
     */
    interface Payloadable {
        object None : Payloadable
    }

    val cardShapeAppearance: ThemeShapeAppearance
        get() = when (cellType) {
            Constants.CellType.TOP -> ThemeShapeAppearance.SmallComponentTop
            Constants.CellType.NONE -> ThemeShapeAppearance.None
            Constants.CellType.BOTTOM -> ThemeShapeAppearance.SmallComponentBottom
            Constants.CellType.BOTTOM_LEFT -> ThemeShapeAppearance.SmallComponentBottomLeft
            Constants.CellType.BOTTOM_RIGHT -> ThemeShapeAppearance.SmallComponentBottomRight
            Constants.CellType.COMPLETE -> ThemeShapeAppearance.Full
            else -> ThemeShapeAppearance.None
        }

    val cardElevation: Float
        get() = if (cellType == Constants.CellType.BOTTOM || cellType == Constants.CellType.COMPLETE) 2F else 0F
}