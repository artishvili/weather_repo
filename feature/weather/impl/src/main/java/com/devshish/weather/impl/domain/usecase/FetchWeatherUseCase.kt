package com.devshish.weather.impl.domain.usecase

import com.devshish.mvvm.text.NativeText
import com.devshish.utils.result.Result
import com.devshish.weather.impl.domain.model.WeatherModel

internal interface FetchWeatherUseCase {

    suspend fun fetch(
        location: String,
        units: String
    ): Result<WeatherModel, NativeText>
}
