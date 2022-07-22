package com.devshish.weather.impl.presentation.weather.adapter.hourly

import androidx.annotation.DrawableRes
import com.devshish.recycler.model.ListItem
import java.util.Date

internal data class UiWeatherHourlyModel(
    val time: Date,
    val temp: Int,
    val rain: Int,
    @DrawableRes val icon: Int,
    override val itemId: String = time.toString()
) : ListItem
