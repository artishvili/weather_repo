package com.devshish.weather.impl.di.component

import androidx.lifecycle.ViewModel
import com.devshish.injector.ComponentHolder
import com.devshish.weather.api.WeatherFeatureAPI
import com.devshish.weather.impl.di.deps.WeatherFeatureDependencies
import com.devshish.weather.impl.di.deps.WeatherFeatureDependenciesStore

internal class WeatherFeatureComponentHolder :
    ViewModel(),
    ComponentHolder<WeatherFeatureAPI, WeatherFeatureDependencies> {

    private val component: WeatherFeatureComponent = WeatherFeatureComponent.initAndGet(
        WeatherFeatureDependenciesStore.dependencies
    )

    fun getComponent(): WeatherFeatureComponent = component

    override fun get(): WeatherFeatureAPI = getComponent()
}
