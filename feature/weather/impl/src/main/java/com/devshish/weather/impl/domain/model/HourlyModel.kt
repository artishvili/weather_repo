package com.devshish.weather.impl.domain.model

import androidx.annotation.DrawableRes
import java.util.Date

internal data class HourlyModel(
    val time: Date,
    val temp: Int,
    val rain: Int,
    @DrawableRes val icon: Int,
)
