package com.devshish.weather.impl.presentation.weather.adapter.hourly

import android.view.View
import com.devshish.recycler.delegate.BaseItemDelegate
import com.devshish.recycler.model.ListItem
import com.devshish.weather.impl.R

internal class WeatherHourlyAdapterDelegate
    : BaseItemDelegate<UiWeatherHourlyModel, WeatherHourlyViewHolder>() {

    override fun getLayoutRes(): Int {
        return R.layout.item_weather_hourly
    }

    override fun getViewHolder(holderView: View): WeatherHourlyViewHolder {
        return WeatherHourlyViewHolder.create(holderView)
    }

    override fun canBeAttachedToItem(item: ListItem): Boolean {
        return item is UiWeatherHourlyModel
    }

    override fun bindItem(item: UiWeatherHourlyModel, holder: WeatherHourlyViewHolder) {
        holder.bind(item)
    }
}
