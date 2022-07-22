package com.devshish.weather.impl.presentation.weather.adapter.daily

import androidx.annotation.DrawableRes
import com.devshish.recycler.model.ListItem
import java.util.Date

internal data class UiDailyModel(
    val day: Date,
    val rainChance: Int,
    val tempMax: Int,
    val tempMin: Int,
    @DrawableRes val icon: Int,
    override val itemId: String = day.toString()
) : ListItem
