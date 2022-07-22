package com.devshish.recycler.model

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class ListItemCallback : DiffUtil.ItemCallback<ListItem>() {

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }
}
