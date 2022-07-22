package com.devshish.weather.impl.domain.model

import androidx.annotation.DrawableRes
import java.util.Date

internal data class WeekWeatherModel(
    val date: Date,
    val tempMax: Int,
    val tempMin: Int,
    val rainChance: Int,
    @DrawableRes val icon: Int,
)
