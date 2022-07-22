package com.devshish.weather.impl.presentation.weather.adapter.hourly

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.devshish.utils.date.TIME_DATE_PATTERN
import com.devshish.utils.date.formatDate
import com.devshish.utils.imageview.loadDrawableRes
import com.devshish.weather.impl.R
import com.devshish.weather.impl.databinding.ItemWeatherHourlyBinding

internal class WeatherHourlyViewHolder(
    private val binding: ItemWeatherHourlyBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(hourWeather: UiWeatherHourlyModel) {
        with(binding) {
            tvTime.text = hourWeather.time.formatDate(TIME_DATE_PATTERN)
            ivWeatherIcon.loadDrawableRes(hourWeather.icon)
            ivRain.loadDrawableRes(com.devshish.ui.R.drawable.ic_rain_drop)

            tvTemp.text = root.context.getString(
                R.string.weather_temp_now,
                hourWeather.temp
            )
            tvRainPossibility.text = root.context.getString(
                R.string.weather_rain_possibility,
                hourWeather.rain
            )
        }
    }

    companion object {
        fun create(holderView: View): WeatherHourlyViewHolder {
            return WeatherHourlyViewHolder(
                binding = ItemWeatherHourlyBinding.bind(holderView)
            )
        }
    }
}
