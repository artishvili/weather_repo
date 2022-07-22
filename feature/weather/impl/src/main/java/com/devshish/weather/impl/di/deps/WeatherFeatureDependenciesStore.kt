package com.devshish.weather.impl.di.deps

import com.devshish.injector.DependenciesStore
import kotlin.properties.Delegates.notNull

object WeatherFeatureDependenciesStore : DependenciesStore<WeatherFeatureDependencies> {

    override var dependencies: WeatherFeatureDependencies by notNull()
}
