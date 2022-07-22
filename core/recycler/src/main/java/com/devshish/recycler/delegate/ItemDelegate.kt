package com.devshish.recycler.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devshish.recycler.model.ListItem

interface ItemDelegate<T : ListItem, WH> where WH : RecyclerView.ViewHolder {
    /**
     * @return unique viewType [Int], used for separating one [ItemDelegate] from another
     */
    fun getViewType(): Int

    /**
     * Decides [ItemDelegate] ability to process this [item]
     *
     * @return true when current [ItemDelegate] can process [item], otherwise false
     */
    fun canBeAttachedToItem(item: ListItem): Boolean

    /**
     * Creates [RecyclerView.ViewHolder]
     */
    fun onCreateViewHolder(parent: ViewGroup): WH

    /**
     * Binds [ListItem] to [RecyclerView.ViewHolder]
     *
     * @param item item for binding
     * @param holder [RecyclerView.ViewHolder] for binding
     */
    fun bindItem(item: T, holder: WH)
}
