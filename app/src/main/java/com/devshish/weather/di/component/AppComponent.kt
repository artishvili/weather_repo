package com.devshish.weather.di.component

import android.app.Application
import com.devshish.settings.impl.di.deps.SettingsFeatureDependencies
import com.devshish.weather.WeatherActivity
import com.devshish.weather.di.feature.SettingsFeatureBindableModule
import com.devshish.weather.di.feature.WeatherFeatureBindableModule
import com.devshish.weather.di.module.ContextModule
import com.devshish.weather.di.module.NavigationModule
import com.devshish.weather.di.module.NetworkModule
import com.devshish.weather.di.module.StorageModule
import com.devshish.weather.impl.di.deps.WeatherFeatureDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Scope
internal annotation class AppScope

@AppScope
@Component(
    modules = [
        NetworkModule::class,
        ContextModule::class,
        StorageModule::class,
        NavigationModule::class,
        WeatherFeatureBindableModule::class,
        SettingsFeatureBindableModule::class
    ]
)
internal interface AppComponent {

    fun inject(activity: WeatherActivity)

    val weatherFeatureDependencies: WeatherFeatureDependencies
    val settingsFeatureDependencies: SettingsFeatureDependencies

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
