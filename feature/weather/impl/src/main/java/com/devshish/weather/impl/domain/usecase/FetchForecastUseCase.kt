package com.devshish.weather.impl.domain.usecase

import com.devshish.mvvm.text.NativeText
import com.devshish.utils.result.Result
import com.devshish.weather.impl.domain.model.ForecastModel

internal interface FetchForecastUseCase {

    suspend fun fetch(
        location: String,
        units: String
    ): Result<List<ForecastModel>, NativeText>
}
