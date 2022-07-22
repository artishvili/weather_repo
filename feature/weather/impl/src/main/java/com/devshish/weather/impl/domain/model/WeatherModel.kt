package com.devshish.weather.impl.domain.model

import androidx.annotation.DrawableRes
import java.util.Date

// TODO: string to int
internal data class WeatherModel(
    val location: String,
    val date: Date,
    val tempNow: Int,
    val tempMax: Int,
    val tempMin: Int,
    val tempFeelsLike: Int,
    val windSpeed: String,
    val uvIndex: String,
    val humidity: String,
    val sunset: String,
    val sunrise: String,
    val conditions: String,
    @DrawableRes val icon: Int,
    val hourly: List<HourlyModel>
)
