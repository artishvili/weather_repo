package com.devshish.weather.impl.domain.model

import java.util.Date

data class ForecastModel(
    val date: Date,
    val tempMax: Int,
    val tempMin: Int,
    val condition: String,
    val icon: Int,
    val sunrise: String,
    val sunset: String,
    val uvIndex: Int,
    val rainChance: Int,
)
