package com.devshish.weather.impl.data.usecase

import com.devshish.mvvm.text.NativeText
import com.devshish.network.error.processRequest
import com.devshish.utils.result.Result
import com.devshish.utils.result.mapSuccess
import com.devshish.weather.impl.data.api.WeatherAPI
import com.devshish.weather.impl.data.mapper.toForecastDomain
import com.devshish.weather.impl.domain.model.ForecastModel
import com.devshish.weather.impl.domain.usecase.FetchForecastUseCase
import javax.inject.Inject

internal class FetchForecastUseCaseImpl @Inject constructor(
    private val api: WeatherAPI
) : FetchForecastUseCase {

    override suspend fun fetch(
        location: String,
        units: String
    ): Result<List<ForecastModel>, NativeText> {
        return processRequest(com.devshish.ui.R.string.message_error) {
            api.fetchWeather(
                location = location,
                units = units
            )
        }
            .mapSuccess {
                it.days.map { day ->
                    day.toForecastDomain()
                }
            }
    }
}
