package com.juanzurita.core.adapter.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.ShapeAppearanceModel
import com.juanzurita.core.ui.theme.ThemeShapeAppearance
import com.juanzurita.core.util.extensions.getAttrId

abstract class BaseViewHolder<T, VB : ViewBinding>(
    container: ViewGroup,
    @LayoutRes layoutId: Int,
    bindingFactory: (View) -> VB
) : RecyclerView.ViewHolder(
    LayoutInflater.from(container.context).inflate(layoutId, container, false)
) {

    protected val binding = bindingFactory(itemView)

    abstract fun bind(item: T)

    protected fun setCardShapeAndElevationOnPosition(
        shapeAppearance: ThemeShapeAppearance,
        elevation: Float
    ) {
        if (binding.root is MaterialCardView) {
            (binding.root as MaterialCardView).apply {
                shapeAppearanceModel = setShapeAppearance(shapeAppearance.value)
                cardElevation = elevation
            }
        }
    }

    private fun setShapeAppearance(shapeAppearanceId: Int): ShapeAppearanceModel {
        val resourceId = binding.root.context.getAttrId(shapeAppearanceId)
        return ShapeAppearanceModel
            .builder(binding.root.context, resourceId, resourceId)
            .build()
    }
}