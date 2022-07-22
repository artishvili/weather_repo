package com.devshish.weather.api

import com.devshish.injector.BaseFeatureAPI

interface WeatherFeatureAPI : BaseFeatureAPI {

    val weatherFeatureStarter: WeatherFeatureStarter
}
