package com.juanzurita.core.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.juanzurita.core.adapter.DelegateAdapterItem

internal class DelegateAdapterItemDiffCallback : DiffUtil.ItemCallback<DelegateAdapterItem>() {

    override fun areItemsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean =
        oldItem::class == newItem::class && oldItem.id() == newItem.id()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean =
        oldItem.content() == newItem.content()

    override fun getChangePayload(oldItem: DelegateAdapterItem, newItem: DelegateAdapterItem): Any =
        oldItem.payload(newItem)
}
