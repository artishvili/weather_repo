package com.devshish.weather.impl.data.usecase

import com.devshish.mvvm.text.NativeText
import com.devshish.network.error.processRequest
import com.devshish.utils.result.Result
import com.devshish.utils.result.mapSuccess
import com.devshish.weather.impl.data.api.WeatherAPI
import com.devshish.weather.impl.data.mapper.toWeekForecastDomain
import com.devshish.weather.impl.domain.model.WeekWeatherModel
import com.devshish.weather.impl.domain.usecase.FetchWeekForecastUseCase
import javax.inject.Inject

internal class FetchWeekForecastUseCaseImpl @Inject constructor(
    private val api: WeatherAPI
) : FetchWeekForecastUseCase {

    override suspend fun fetch(
        location: String,
        units: String
    ): Result<List<WeekWeatherModel>, NativeText> {
        return processRequest(com.devshish.ui.R.string.message_error) {
            api.fetchWeather(
                location = location,
                units = units
            )
        }
            .mapSuccess { it.toWeekForecastDomain().take(WEEK) }
    }

    companion object {
        private const val WEEK = 7
    }
}
