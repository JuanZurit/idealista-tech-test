package com.juanzurita.core.util.extensions

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import kotlin.math.abs


fun Int?.orValue(num: Int) = this ?: num

fun String?.orValue(value: String) = this ?: value

fun Float?.round(decimals: Int): BigDecimal {
    if (this == null) return BigDecimal.ZERO
    val bd = BigDecimal.valueOf(this.toDouble())
    return bd.setScale(decimals, RoundingMode.HALF_UP)
}

fun Double?.removeZeroDecimal(): String {
    if (this == null) return ""
    if (this - this.toInt() == 0.0) {
        return this.toInt().toString()
    }
    return this.toString()
}

fun Float.formatWithSeparators(): String {
    val symbols = DecimalFormatSymbols(Locale.US).apply {
        groupingSeparator = '.'
        decimalSeparator = ','
    }
    val formatter = DecimalFormat("#,##0.##", symbols)
    return formatter.format(this)}