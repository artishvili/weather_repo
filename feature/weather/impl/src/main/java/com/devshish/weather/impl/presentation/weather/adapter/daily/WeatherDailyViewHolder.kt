package com.devshish.weather.impl.presentation.weather.adapter.daily

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.devshish.utils.date.DAY_PATTERN
import com.devshish.utils.date.formatDate
import com.devshish.utils.imageview.loadDrawableRes
import com.devshish.weather.impl.R
import com.devshish.weather.impl.databinding.ItemWeatherDailyBinding

internal class WeatherDailyViewHolder(
    private val binding: ItemWeatherDailyBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(day: UiDailyModel) {
        with(binding) {
            tvDay.text = day.day.formatDate(DAY_PATTERN)
            tvRainPossibility.text = root.context.getString(
                R.string.weather_rain_possibility,
                day.rainChance
            )
            tvTempDayNight.text = root.context.getString(
                R.string.weather_temp_max_min,
                day.tempMax,
                day.tempMin
            )

            ivRain.loadDrawableRes(com.devshish.ui.R.drawable.ic_rain_drop)
            ivWeatherIcon.loadDrawableRes(day.icon)
        }
    }

    companion object {
        fun create(holderView: View): WeatherDailyViewHolder {
            return WeatherDailyViewHolder(
                binding = ItemWeatherDailyBinding.bind(holderView)
            )
        }
    }
}
