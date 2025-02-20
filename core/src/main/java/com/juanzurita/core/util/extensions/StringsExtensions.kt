package com.juanzurita.core.util.extensions

fun String?.intValue(default: Int = 0): Int {
    return try {
        this?.toIntOrNull() ?: default
    } catch (e: Exception) {
        default
    }
}

fun String?.longValue(default: Long = 0): Long {
    return try {
        this?.toLongOrNull() ?: default
    } catch (e: Exception) {
        default
    }
}

fun String?.doubleValue(default: Double = 0.0): Double {
    return try {
        this?.toDoubleOrNull() ?: default
    } catch (e: Exception) {
        default
    }
}

fun String?.floatValue(default: Float = 0f): Float {
    return try {
        this?.toFloatOrNull() ?: default
    } catch (e: Exception) {
        default
    }
}

