package com.juanzurita.core.util
object Constants {
    interface CellType {
        companion object {
            const val TOP: Int = 1
            const val BOTTOM: Int = 2
            const val MIDDEL: Int = 0
            const val COMPLETE: Int = 3
            const val NONE: Int = 4
            const val BOTTOM_LEFT: Int = 5
            const val BOTTOM_RIGHT: Int = 6
        }
    }
}