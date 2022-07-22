package com.devshish.weather.impl.presentation.weather.adapter.daily

import android.view.View
import com.devshish.recycler.delegate.BaseItemDelegate
import com.devshish.recycler.model.ListItem
import com.devshish.weather.impl.R

internal class WeatherDailyAdapterDelegate
    : BaseItemDelegate<UiDailyModel, WeatherDailyViewHolder>() {

    override fun getLayoutRes(): Int {
        return R.layout.item_weather_daily
    }

    override fun getViewHolder(holderView: View): WeatherDailyViewHolder {
        return WeatherDailyViewHolder.create(holderView)
    }

    override fun canBeAttachedToItem(item: ListItem): Boolean {
        return item is UiDailyModel
    }

    override fun bindItem(item: UiDailyModel, holder: WeatherDailyViewHolder) {
        holder.bind(item)
    }
}
