package com.juanzurita.core.util.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import java.io.File

fun ImageView.prepare(): ImageBuilder {
    return ImageBuilder(this)
}

fun ImageView.load(bitmap: Bitmap?) {
    ImageBuilder(this).load(bitmap)
}

fun ImageView.load(drawable: Drawable?) {
    ImageBuilder(this).load(drawable)
}

fun ImageView.load(string: String?) {
    ImageBuilder(this).load(string)
}

fun ImageView.load(uri: Uri?) {
    ImageBuilder(this).load(uri)
}

fun ImageView.load(file: File?) {
    ImageBuilder(this).load(file)
}

fun ImageView.load(@RawRes @DrawableRes resourceId: Int?) {
    ImageBuilder(this).load(resourceId)
}

fun ImageView.load(model: ByteArray?) {
    ImageBuilder(this).load(model)
}

fun ImageView.loadWithoutCache(any: Any?) {
    ImageBuilder(this).loadWithoutCache(any)
}


interface ImageLoader {
    fun rounded(radius: Int = 20000): ImageLoader
    fun fadeIn(): ImageLoader
    fun resize(size: Int): ImageLoader
    fun resize(width: Int, height: Int): ImageLoader
    fun placeholder(@DrawableRes drawable: Int): ImageLoader
    fun fallback(@DrawableRes drawable: Int): ImageLoader
    fun error(@DrawableRes drawable: Int): ImageLoader
    fun centerCrop(): ImageLoader
    fun circleCrop(): ImageLoader
    fun fitCenter(): ImageLoader
    fun load(any: Any?)
    fun loadWithoutCache(any: Any?)
}

class ImageBuilder(private var imageView: ImageView) : ImageLoader {

    // scale types
    private var fitCenter: Boolean = false
    private var centerCrop: Boolean = false
    private var centerInside: Boolean = false
    private var circleCrop: Boolean = false

    // transformations
    private var roundedRadius: Int = -1
    private var height: Int = -1
    private var width: Int = -1

    // transitions
    private var fadeIn: Boolean = false

    // preload & postload
    private var placeholder: Int = -1
    private var fallback: Int = -1
    private var error: Int = -1


    override fun rounded(radius: Int): ImageBuilder {
        this.roundedRadius = radius
        return this
    }

    override fun fadeIn(): ImageBuilder {
        this.fadeIn = true
        return this
    }

    override fun resize(size: Int): ImageBuilder {
        this.width = size
        this.height = size
        return this
    }

    override fun resize(width: Int, height: Int): ImageBuilder {
        this.width = width
        this.height = height
        return this
    }

    override fun error(@DrawableRes drawable: Int): ImageBuilder {
        this.error = drawable
        return this
    }

    override fun placeholder(@DrawableRes drawable: Int): ImageBuilder {
        this.placeholder = drawable
        return this
    }

    override fun fallback(@DrawableRes drawable: Int): ImageBuilder {
        this.fallback = drawable
        return this
    }

    override fun centerCrop(): ImageBuilder {
        this.centerCrop = true
        return this
    }

    override fun circleCrop(): ImageBuilder {
        this.circleCrop = true
        return this
    }

    override fun fitCenter(): ImageBuilder {
        this.fitCenter = true
        return this
    }

    override fun load(any: Any?) {

        // image & request options
        var glide = Glide.with(imageView.context)
            .load(any)
            .apply(getRequestOptions())

        // transformations
        val transformations = getTransformations()
        if (transformations.isNotEmpty()) {
            glide = glide.transform(MultiTransformation(transformations))
        }

        // transitions
        val transition = getTransition()
        if (transition != null) {
            glide = glide.transition(transition)
        }

        // load configuration into image
        glide.into(imageView)

    }

    override fun loadWithoutCache(any: Any?) {

        // image & request options
        var glide = Glide.with(imageView.context)
            .load(any)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply(getRequestOptions())

        // transformations
        val transformations = getTransformations()
        if (transformations.isNotEmpty()) {
            glide = glide.transform(MultiTransformation(transformations))
        }

        // transitions
        val transition = getTransition()
        if (transition != null) {
            glide = glide.transition(transition)
        }

        // load configuration into image
        glide.into(imageView)

    }

    private fun getRequestOptions(): RequestOptions {
        var options = RequestOptions()
        if (placeholder != -1) options = options.placeholder(placeholder)
        if (fallback != -1) options = options.fallback(fallback)
        if (error != -1) options = options.error(error)
        if (width != -1 && height != -1) options = options.override(width, height)
        return options
    }

    private fun getTransformations(): MutableList<Transformation<Bitmap>> {

        // prepare transformations
        val transformations = mutableListOf<Transformation<Bitmap>>()

        // scale type
        if (centerInside) {
            transformations.add(CenterInside())
        }
        if (centerCrop) {
            transformations.add(CenterCrop())
        }
        if (fitCenter) {
            transformations.add(FitCenter())
        }
        if (circleCrop) {
            transformations.add(CircleCrop())
        }
        // rounded
        if (roundedRadius >= 1) {
            transformations.add(RoundedCorners(roundedRadius))
        }

        return transformations
    }

    private fun getTransition(): DrawableTransitionOptions? {
        return if (fadeIn) {
            DrawableTransitionOptions.withCrossFade(100)
        } else {
            null
        }
    }
}
