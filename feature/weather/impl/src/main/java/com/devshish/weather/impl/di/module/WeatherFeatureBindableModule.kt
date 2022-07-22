package com.devshish.weather.impl.di.module

import com.devshish.weather.api.WeatherFeatureStarter
import com.devshish.weather.impl.data.usecase.FetchForecastUseCaseImpl
import com.devshish.weather.impl.data.usecase.FetchWeatherUseCaseImpl
import com.devshish.weather.impl.data.usecase.FetchWeekForecastUseCaseImpl
import com.devshish.weather.impl.domain.usecase.FetchForecastUseCase
import com.devshish.weather.impl.domain.usecase.FetchWeatherUseCase
import com.devshish.weather.impl.domain.usecase.FetchWeekForecastUseCase
import com.devshish.weather.impl.presentation.WeatherFeatureStarterImpl
import dagger.Binds
import dagger.Module

@Module
internal interface WeatherFeatureBindableModule {

    @Binds
    fun bindWeatherFeatureStarterImpl(
        weatherFeatureStarterImpl: WeatherFeatureStarterImpl
    ): WeatherFeatureStarter

    @Binds
    fun bindFetchWeatherUseCaseImpl(
        useCaseImpl: FetchWeatherUseCaseImpl
    ): FetchWeatherUseCase

    @Binds
    fun bindFetchWeekForecastUseCaseImpl(
        useCaseImpl: FetchWeekForecastUseCaseImpl
    ): FetchWeekForecastUseCase

    @Binds
    fun bindFetchForecastUseCaseImpl(
        useCaseImpl: FetchForecastUseCaseImpl
    ): FetchForecastUseCase
}
