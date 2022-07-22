package com.devshish.recycler.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.devshish.recycler.model.ListItem

abstract class BaseItemDelegate<T : ListItem, WH : RecyclerView.ViewHolder> : ItemDelegate<T, WH> {

    /**
     * Override this to provide Layout resource
     */
    @LayoutRes
    abstract fun getLayoutRes(): Int

    /**
     * Creates [RecyclerView.ViewHolder] with provided [View]
     *
     * @param holderView is view that used for instantiation of [RecyclerView.ViewHolder]
     */
    abstract fun getViewHolder(holderView: View): WH

    final override fun getViewType(): Int = getLayoutRes()

    final override fun onCreateViewHolder(parent: ViewGroup): WH {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutRes(), parent, false)
        return getViewHolder(view)
    }
}
