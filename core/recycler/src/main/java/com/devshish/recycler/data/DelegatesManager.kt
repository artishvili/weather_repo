package com.devshish.recycler.data

import android.util.SparseArray
import android.view.ViewGroup
import androidx.core.util.forEach
import androidx.recyclerview.widget.RecyclerView
import com.devshish.recycler.delegate.ItemDelegate
import com.devshish.recycler.model.ListItem

class DelegatesManager(
    delegateList: Array<out ItemDelegate<out ListItem, out RecyclerView.ViewHolder>>
) {

    private val delegates = SparseArray<ItemDelegate<out ListItem, out RecyclerView.ViewHolder>>()
        .also { array ->
            delegateList.forEach { array.put(it.getViewType(), it) }
        }

    /**
     * Returns unique ViewType for [ListItem]
     *
     * @throws error when no supported [ItemDelegate] for this [item] found
     */
    fun getViewTypeByItem(item: ListItem): Int {
        delegates.forEach { key, itemDelegate ->
            val canBeAttachedToItem = itemDelegate.canBeAttachedToItem(item)
            if (canBeAttachedToItem) {
                return key
            }
        }
        error("You can't attach list item $item")
    }

    /**
     * Asks delegate to create [RecyclerView.ViewHolder]
     */
    fun createItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val delegate = delegates[viewType]
        return delegate.onCreateViewHolder(parent)
    }

    /**
     * Binds [ListItem] to [holder]
     */
    @Suppress("unchecked_cast")
    fun bindItem(
        holder: RecyclerView.ViewHolder,
        item: ListItem,
    ) {
        val key = getViewTypeByItem(item)
        val delegate =
            delegates[key] as ItemDelegate<ListItem, RecyclerView.ViewHolder>
        delegate.bindItem(item = item, holder = holder)
    }
}
