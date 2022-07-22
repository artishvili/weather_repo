package com.devshish.weather.impl.presentation.ext

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.devshish.utils.imageview.loadDrawableRes
import com.devshish.weather.impl.databinding.IncludeWeatherInfoBinding

internal fun IncludeWeatherInfoBinding.bind(
    @DrawableRes iconRes: Int,
    @StringRes titleRes: Int,
    value: String
) {
    ivWeatherInfoIcon.loadDrawableRes(iconRes)
    tvWeatherInfo.text = root.context.getString(titleRes)
    tvWeatherInfoValue.text = value
}
