package com.devshish.weather.app

import android.app.Application
import android.content.Context
import com.devshish.settings.impl.di.deps.SettingsFeatureDependenciesStore
import com.devshish.weather.BuildConfig
import com.devshish.weather.di.component.AppComponent
import com.devshish.weather.di.component.DaggerAppComponent
import com.devshish.weather.impl.di.deps.WeatherFeatureDependenciesStore
import timber.log.Timber

class WeatherApp : Application() {

    internal val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        initWeatherFeature()
        initSettingsFeature()

        plantTimberLogging()
    }

    private fun initWeatherFeature() {
        WeatherFeatureDependenciesStore.dependencies = appComponent.weatherFeatureDependencies
    }

    private fun initSettingsFeature() {
        SettingsFeatureDependenciesStore.dependencies = appComponent.settingsFeatureDependencies
    }

    private fun plantTimberLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        internal fun getComponent(context: Context): AppComponent {
            return (context.applicationContext as WeatherApp).appComponent
        }
    }
}
