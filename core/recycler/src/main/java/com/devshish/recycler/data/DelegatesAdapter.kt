package com.devshish.recycler.data

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devshish.recycler.delegate.ItemDelegate
import com.devshish.recycler.model.ListItem
import com.devshish.recycler.model.ListItemCallback

class DelegatesAdapter(
    vararg delegates: ItemDelegate<out ListItem, out RecyclerView.ViewHolder>
) : ListAdapter<ListItem, RecyclerView.ViewHolder>(ListItemCallback()) {

    private val manager: DelegatesManager = DelegatesManager(delegates)

    override fun getItemViewType(position: Int): Int {
        return manager.getViewTypeByItem(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return manager.createItemViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        manager.bindItem(
            holder = holder,
            item = getItem(position)
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        manager.bindItem(
            holder = holder,
            item = getItem(position)
        )
    }
}
