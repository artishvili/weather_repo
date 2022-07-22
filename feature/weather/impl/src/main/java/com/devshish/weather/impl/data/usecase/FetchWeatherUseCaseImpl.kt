package com.devshish.weather.impl.data.usecase

import com.devshish.mvvm.text.NativeText
import com.devshish.network.error.processRequest
import com.devshish.utils.result.Result
import com.devshish.utils.result.mapSuccess
import com.devshish.weather.impl.data.api.WeatherAPI
import com.devshish.weather.impl.data.mapper.toWeatherDomain
import com.devshish.weather.impl.domain.model.WeatherModel
import com.devshish.weather.impl.domain.usecase.FetchWeatherUseCase
import javax.inject.Inject

internal class FetchWeatherUseCaseImpl @Inject constructor(
    private val api: WeatherAPI,
//    private val settingsDataStorage: SettingsDataStorage
) : FetchWeatherUseCase {

    override suspend fun fetch(
        location: String,
        units: String
    ): Result<WeatherModel, NativeText> {
        return processRequest(com.devshish.ui.R.string.message_error) {
            api.fetchWeather(
                location = location,
                units = units
            )
        }
            .mapSuccess { it.toWeatherDomain() }
    }
}
