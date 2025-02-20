package com.juanzurita.core.adapter

import android.util.Log
import android.util.SparseArray
import android.view.ViewGroup
import androidx.core.util.forEach
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

open class CompositeAdapter(
    private val delegates: SparseArray<DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>>,
) : ListAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>(DelegateAdapterItemDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        for (i in 0 until delegates.size()) {
            try{
                if (delegates[i].modelClass == getItem(position).javaClass && delegates[i].isForViewType(
                        getItem(position)
                    )
                ) {
                    return delegates.keyAt(i)
                }
            } catch (e: ClassCastException) {
                Log.d("ClassCastException", e.message.orEmpty())
            }
        }
        throw NullPointerException("Can not get viewType for position $position, item " + getItem(0)?.javaClass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegates[viewType].createViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        onBindViewHolder(holder, position, mutableListOf())

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        try {
            val delegateAdapter = delegates[getItemViewType(position)]

            if (delegateAdapter != null) {
                val delegatePayloads =
                    payloads.mapNotNull { it as? DelegateAdapterItem.Payloadable }
                val item = getItem(position)
                if (delegateAdapter.isForViewType(item)) {
                    delegateAdapter.bindViewHolder(item, holder, delegatePayloads)
                }
            } else {
                throw NullPointerException("can not find adapter for position $position")
            }
        }catch (e: ClassCastException) {
            Log.d("ClassCastException_onBindViewHolder", e.message.orEmpty())
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        delegates[holder.itemViewType].onViewRecycled(holder)
        super.onViewRecycled(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        delegates[holder.itemViewType].onViewDetachedFromWindow(holder)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        delegates.forEach { _, value -> value.onDetachedFromRecyclerView(recyclerView) }
        super.onDetachedFromRecyclerView(recyclerView)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        delegates[holder.itemViewType].onViewAttachedToWindow(holder)
        super.onViewAttachedToWindow(holder)
    }

    /**
     * Add a new data list to the end of current adapter list.
     *
     * @param data list to add
     */
    open fun addAll(data: List<DelegateAdapterItem>?) {
        val dataList = mutableListOf<DelegateAdapterItem>()
        dataList.addAll(currentList) // Add old data
        dataList.addAll(data.orEmpty()) // Add new data
        submitList(dataList) // Submit change to diffutils
    }

    /**
     * Delete item from adapter on indicated position
     *
     * @param position Position to delete
     */
    open fun deleteItem(position: Int) {
        if (position in 0 until itemCount) {
            val dataList = mutableListOf<DelegateAdapterItem>()
            dataList.addAll(currentList)
            dataList.removeAt(position)
            submitList(dataList)
        }
    }

    /**
     * Add a news item to @position the current list adapter.
     *
     * @param item item to be added
     * @param position Position to insert the item in adapter. If its empty or not valid position, add the item to end of the list
     */
    open fun addItem(item: DelegateAdapterItem, position: Int = -1) {
        val dataList = mutableListOf<DelegateAdapterItem>()
        dataList.addAll(currentList)
        if (position in 0 until itemCount) {
            dataList.add(position, item)
        } else {
            dataList.add(item)
        }
        submitList(dataList)
    }

    /**
     * Update the item in adapter if exist. Otherwise add it to end of the list
     */
    open fun update(newItem: DelegateAdapterItem) {
        val dataList = mutableListOf<DelegateAdapterItem>()
        dataList.addAll(currentList)

        var position = -1
        dataList.forEachIndexed { adapterPos, item ->
            if (item.id() == newItem.id()) {
                position = adapterPos
            }
        }
        if (position != -1) {
            dataList.removeAt(position)
            dataList.add(position, newItem)
        } else {
            dataList.add(newItem)
        }
        submitList(dataList)
    }

    /**
     * Clear adapter
     */
    open fun clearData() {
        submitList(mutableListOf<DelegateAdapterItem>())
    }

    class Builder {

        private var count: Int = 0
        private val delegates: SparseArray<DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>> =
            SparseArray()

        fun add(delegateAdapter: DelegateAdapter<out DelegateAdapterItem, *>): Builder {
            delegates.put(
                count++,
                delegateAdapter as DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>
            )
            return this
        }

        fun build(): CompositeAdapter {
            require(count != 0) { "Register at least one adapter" }
            return CompositeAdapter(delegates)
        }
    }

}