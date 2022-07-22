package com.devshish.weather.impl.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devshish.mvvm.di.ViewModelFactory
import com.devshish.mvvm.di.ViewModelKey
import com.devshish.weather.impl.di.component.WeatherFeatureScope
import com.devshish.weather.impl.presentation.forecast.ForecastViewModel
import com.devshish.weather.impl.presentation.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface WeatherFeatureViewModelModule {

    @Binds
    @WeatherFeatureScope
    fun bindViewModelFactory(viewModel: ViewModelFactory): ViewModelProvider.Factory

    @[Binds IntoMap]
    @ViewModelKey(WeatherViewModel::class)
    fun bindWeatherViewModel(viewModel: WeatherViewModel): ViewModel

    @[Binds IntoMap]
    @ViewModelKey(ForecastViewModel::class)
    fun bindForecastViewModel(viewModel: ForecastViewModel): ViewModel
}
