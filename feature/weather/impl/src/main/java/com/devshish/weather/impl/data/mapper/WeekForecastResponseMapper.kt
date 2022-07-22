package com.devshish.weather.impl.data.mapper

import com.devshish.utils.date.CURRENT_DATE_PATTERN
import com.devshish.utils.date.parseToDate
import com.devshish.utils.image.mapToWeatherPicture
import com.devshish.weather.impl.data.model.WeatherResponseModel
import com.devshish.weather.impl.domain.model.WeekWeatherModel
import kotlin.math.roundToInt

internal fun WeatherResponseModel.toWeekForecastDomain(): List<WeekWeatherModel> {
    return days.map {
        WeekWeatherModel(
            date = it.datetime.parseToDate(CURRENT_DATE_PATTERN),
            tempMax = it.tempMax.roundToInt(),
            tempMin = it.tempMin.roundToInt(),
            rainChance = it.precip.roundToInt(),
            icon = mapToWeatherPicture(it.icon)
        )
    }
}
