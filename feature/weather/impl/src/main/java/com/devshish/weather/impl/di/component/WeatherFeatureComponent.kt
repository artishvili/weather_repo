package com.devshish.weather.impl.di.component

import com.devshish.weather.api.WeatherFeatureAPI
import com.devshish.weather.api.WeatherFeatureStarter
import com.devshish.weather.impl.di.deps.WeatherFeatureDependencies
import com.devshish.weather.impl.di.module.WeatherFeatureBindableModule
import com.devshish.weather.impl.di.module.WeatherFeatureNetworkModule
import com.devshish.weather.impl.di.module.WeatherFeatureViewModelModule
import com.devshish.weather.impl.presentation.forecast.ForecastFragment
import com.devshish.weather.impl.presentation.weather.WeatherFragment
import dagger.Component
import javax.inject.Scope

@Scope
annotation class WeatherFeatureScope

@Component(
    modules = [
        WeatherFeatureBindableModule::class,
        WeatherFeatureViewModelModule::class,
        WeatherFeatureNetworkModule::class
    ],
    dependencies = [WeatherFeatureDependencies::class]
)
@WeatherFeatureScope
internal interface WeatherFeatureComponent : WeatherFeatureAPI {

    override val weatherFeatureStarter: WeatherFeatureStarter

    /** Entry point for Weather feature */
    fun inject(fragment: WeatherFragment)

    fun inject(fragment: ForecastFragment)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: WeatherFeatureDependencies): Builder

        fun build(): WeatherFeatureComponent
    }

    companion object {
        fun initAndGet(dependencies: WeatherFeatureDependencies): WeatherFeatureComponent {
            return DaggerWeatherFeatureComponent.builder()
                .dependencies(dependencies)
                .build()
        }
    }
}
