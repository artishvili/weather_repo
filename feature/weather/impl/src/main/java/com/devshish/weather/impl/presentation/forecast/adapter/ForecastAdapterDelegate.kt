package com.devshish.weather.impl.presentation.forecast.adapter

import android.view.View
import com.devshish.recycler.delegate.BaseItemDelegate
import com.devshish.recycler.model.ListItem
import com.devshish.weather.impl.R

internal class ForecastAdapterDelegate
    : BaseItemDelegate<UiForecastModel, ForecastViewHolder>() {

    override fun getLayoutRes(): Int {
        return R.layout.item_weather_forecast
    }

    override fun getViewHolder(holderView: View): ForecastViewHolder {
        return ForecastViewHolder.create(holderView)
    }

    override fun canBeAttachedToItem(item: ListItem): Boolean {
        return item is UiForecastModel
    }

    override fun bindItem(item: UiForecastModel, holder: ForecastViewHolder) {
        holder.bind(item)
    }
}
