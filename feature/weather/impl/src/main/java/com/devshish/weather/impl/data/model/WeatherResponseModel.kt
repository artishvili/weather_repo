package com.devshish.weather.impl.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class WeatherResponseModel(
    @SerialName("address")
    val address: String,
    @SerialName("days")
    val days: List<DaysResponseModel>
)

@Serializable
internal data class DaysResponseModel(
    @SerialName("datetime")
    val datetime: String,
    @SerialName("tempmax")
    val tempMax: Double,
    @SerialName("tempmin")
    val tempMin: Double,
    @SerialName("temp")
    val temp: Double,
    @SerialName("feelslike")
    val feelsLike: Double,
    @SerialName("windspeed")
    val windSpeed: Double,
    @SerialName("uvindex")
    val uvIndex: Double,
    @SerialName("humidity")
    val humidity: Double,
    @SerialName("precipprob")
    val precip: Double,
    @SerialName("sunrise")
    val sunrise: String,
    @SerialName("sunset")
    val sunset: String,
    @SerialName("conditions")
    val conditions: String,
    @SerialName("icon")
    val icon: String,
    @SerialName("hours")
    val hours: List<HoursResponseModel>
)

@Serializable
internal data class HoursResponseModel(
    @SerialName("datetime")
    val datetime: String,
    @SerialName("temp")
    val temp: Double,
    @SerialName("precipprob")
    val precip: Double,
    @SerialName("icon")
    val icon: String,
)
