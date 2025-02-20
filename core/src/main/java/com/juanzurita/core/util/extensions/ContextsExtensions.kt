package com.juanzurita.core.util.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat


@ColorInt
fun Context.getAttrColor(@AttrRes id: Int): Int {
    val resolvedAttr = TypedValue()
    this.theme.resolveAttribute(id, resolvedAttr, true)
    val colorRes = resolvedAttr.run {
        if (resourceId != 0) resourceId else data
    }
    return ContextCompat.getColor(this, colorRes)
}


/**
 * @param id Id del atributo perteniciente al tema. En caso de querer referenciar a los atributos por defecto de MD3, el id debe empezar por com.google.android.material seguido de R.attr.{attr_name}
 * @return Id de la propiedad referenciada en el atributo perteneciente al tema aplicado a la actividad (declarado en el Manifest)
 */
fun Context.getAttrId(@AttrRes id: Int): Int {
    val resolvedAttr = TypedValue()
    this.theme.resolveAttribute(id, resolvedAttr, true)
    val resource = resolvedAttr.run {
        if (resourceId != 0) resourceId else data
    }
    return resource
}


fun Context.getAttrDrawable(@AttrRes id: Int): Drawable? {
    val resolvedAttr = TypedValue()
    this.theme.resolveAttribute(id, resolvedAttr, false)
    val drawableRes = resolvedAttr.run {
        if (resourceId != 0) resourceId else data
    }
    return ContextCompat.getDrawable(this, drawableRes)
}

fun Context.getAttrDrawableId(@AttrRes id: Int): Int {
    val resolvedAttr = TypedValue()
    this.theme.resolveAttribute(id, resolvedAttr, false)
    val drawableRes = resolvedAttr.run {
        if (resourceId != 0) resourceId else data
    }
    return drawableRes
}

fun Context.getAttrColorListState(@AttrRes id: Int): ColorStateList? {
    val resolvedAttr = TypedValue()
    this.theme.resolveAttribute(id, resolvedAttr, true)
    val colorListState = resolvedAttr.run {
        if (resourceId != 0) resourceId else data
    }
    return ContextCompat.getColorStateList(this, colorListState)
}

fun Context.getColorAsListState(colorId: Int): ColorStateList? {
    return try {
        val colorInt: Int = ContextCompat.getColor(this, colorId)
        ColorStateList.valueOf(colorInt)
    } catch (e: java.lang.Exception) {
        null
    }
}
