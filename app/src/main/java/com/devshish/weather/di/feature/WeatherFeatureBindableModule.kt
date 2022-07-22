package com.devshish.weather.di.feature

import com.devshish.weather.api.WeatherFeatureStarter
import com.devshish.weather.di.component.AppScope
import com.devshish.weather.impl.presentation.WeatherFeatureStarterImpl
import dagger.Binds
import dagger.Module

@Module
interface WeatherFeatureBindableModule {

    @[Binds AppScope]
    fun bindWeatherFeatureStarterImpl(
        starter: WeatherFeatureStarterImpl
    ): WeatherFeatureStarter
}
