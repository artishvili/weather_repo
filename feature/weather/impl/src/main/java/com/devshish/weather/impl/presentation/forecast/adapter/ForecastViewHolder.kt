package com.devshish.weather.impl.presentation.forecast.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.devshish.utils.date.CURRENT_BLOCK_DATE_PATTERN
import com.devshish.utils.date.formatDate
import com.devshish.utils.imageview.loadDrawableRes
import com.devshish.weather.impl.R
import com.devshish.weather.impl.databinding.ItemWeatherForecastBinding
import com.devshish.weather.impl.presentation.ext.bind

internal class ForecastViewHolder(
    private val binding: ItemWeatherForecastBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(forecast: UiForecastModel) {
        with(binding) {
            tvDate.text = forecast.date.formatDate(CURRENT_BLOCK_DATE_PATTERN)
            tvCondition.text = forecast.condition
            tvTempDayNight.text = root.context.getString(
                R.string.weather_temp_max_min,
                forecast.tempMax,
                forecast.tempMin
            )

            ivConditionIcon.loadDrawableRes(forecast.icon)

            layoutSunrise.bind(
                iconRes = com.devshish.ui.R.drawable.ic_sunrise,
                titleRes = R.string.weather_info_sunrise,
                value = forecast.sunrise
            )

            layoutSunset.bind(
                iconRes = com.devshish.ui.R.drawable.ic_sunset,
                titleRes = R.string.weather_info_sunset,
                value = forecast.sunset
            )

            layoutUV.bind(
                iconRes = com.devshish.ui.R.drawable.ic_uv_index,
                titleRes = R.string.weather_info_uv,
                value = forecast.uvIndex.toString()
            )
        }
    }

    companion object {
        fun create(holderView: View): ForecastViewHolder {
            return ForecastViewHolder(
                binding = ItemWeatherForecastBinding.bind(holderView)
            )
        }
    }
}
