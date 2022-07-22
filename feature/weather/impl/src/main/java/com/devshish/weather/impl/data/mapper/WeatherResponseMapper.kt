package com.devshish.weather.impl.data.mapper

import com.devshish.utils.date.CURRENT_DATE_PATTERN
import com.devshish.utils.date.RESPONSE_TIME_PATTERN
import com.devshish.utils.date.parseToDate
import com.devshish.utils.image.mapToWeatherPicture
import com.devshish.weather.impl.data.model.DaysResponseModel
import com.devshish.weather.impl.data.model.HoursResponseModel
import com.devshish.weather.impl.data.model.WeatherResponseModel
import com.devshish.weather.impl.domain.model.ForecastModel
import com.devshish.weather.impl.domain.model.HourlyModel
import com.devshish.weather.impl.domain.model.WeatherModel
import kotlin.math.roundToInt

internal fun WeatherResponseModel.toWeatherDomain(): WeatherModel {
    return days.map {
        WeatherModel(
            date = it.datetime.parseToDate(CURRENT_DATE_PATTERN),
            tempNow = it.temp.roundToInt(),
            tempMax = it.tempMax.roundToInt(),
            tempMin = it.tempMin.roundToInt(),
            tempFeelsLike = it.feelsLike.roundToInt(),
            windSpeed = it.windSpeed.toString(),
            uvIndex = it.uvIndex.toString(),
            humidity = it.humidity.toString(),
            location = address,
            sunset = it.sunset,
            sunrise = it.sunrise,
            conditions = it.conditions,
            icon = mapToWeatherPicture(it.icon),
            hourly = it.hours.map { hour -> hour.toWeatherDomain() }
        )
    }.first()
}

internal fun HoursResponseModel.toWeatherDomain(): HourlyModel {
    return HourlyModel(
        time = datetime.parseToDate(RESPONSE_TIME_PATTERN),
        temp = temp.roundToInt(),
        rain = precip.roundToInt(),
        icon = mapToWeatherPicture(icon)
    )
}

internal fun DaysResponseModel.toForecastDomain(): ForecastModel {
    return ForecastModel(
        date = datetime.parseToDate(CURRENT_DATE_PATTERN),
        tempMax = tempMax.roundToInt(),
        tempMin = tempMin.roundToInt(),
        uvIndex = uvIndex.roundToInt(),
        rainChance = precip.roundToInt(),
        icon = mapToWeatherPicture(icon),
        condition = conditions,
        sunrise = sunrise,
        sunset = sunset,
    )
}
