package com.devshish.weather.impl.domain.mapper

import com.devshish.weather.impl.domain.model.ForecastModel
import com.devshish.weather.impl.domain.model.HourlyModel
import com.devshish.weather.impl.domain.model.WeekWeatherModel
import com.devshish.weather.impl.presentation.forecast.adapter.UiForecastModel
import com.devshish.weather.impl.presentation.weather.adapter.daily.UiDailyModel
import com.devshish.weather.impl.presentation.weather.adapter.hourly.UiWeatherHourlyModel

internal fun HourlyModel.mapToUi(): UiWeatherHourlyModel {
    return UiWeatherHourlyModel(
        time = time,
        temp = temp,
        rain = rain,
        icon = icon
    )
}

internal fun WeekWeatherModel.mapToUi(): UiDailyModel {
    return UiDailyModel(
        day = date,
        rainChance = rainChance,
        tempMax = tempMax,
        tempMin = tempMin,
        icon = icon
    )
}

internal fun ForecastModel.mapToUi(): UiForecastModel {
    return UiForecastModel(
        date = date,
        tempMax = tempMax,
        tempMin = tempMin,
        condition = condition,
        icon = icon,
        sunrise = sunrise,
        sunset = sunset,
        uvIndex = uvIndex,
        rainChance = rainChance
    )
}
