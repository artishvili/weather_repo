package com.devshish.weather.impl.di.module

import com.devshish.network.createRetrofitService
import com.devshish.weather.impl.data.api.WeatherAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class WeatherFeatureNetworkModule {

    @Provides
    fun provideWeatherAPI(retrofit: Retrofit): WeatherAPI {
        return createRetrofitService(retrofit, WeatherAPI::class.java)
    }
}
