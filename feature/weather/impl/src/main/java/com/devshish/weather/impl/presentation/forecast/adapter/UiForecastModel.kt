package com.devshish.weather.impl.presentation.forecast.adapter

import com.devshish.recycler.model.ListItem
import java.util.Date

internal data class UiForecastModel(
    val date: Date,
    val tempMax: Int,
    val tempMin: Int,
    val condition: String,
    val icon: Int,
    val sunrise: String,
    val sunset: String,
    val uvIndex: Int,
    val rainChance: Int,
    override val itemId: String = date.toString()
) : ListItem
