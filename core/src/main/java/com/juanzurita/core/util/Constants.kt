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


    interface Arguments{
        companion object{
            const val IS_FAVORITE = "isFavorite"
        }
    }

    interface URL{
        companion object{
            const val NO_PHOTO = "https://cdn1.polaris.com/globalassets/pga/accessories/my20-orv-images/no_image_available6.jpg?v=71397d75&format=webp&height=800"
        }
    }
}