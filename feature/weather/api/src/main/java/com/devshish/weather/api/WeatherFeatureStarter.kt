package com.devshish.weather.api

import com.github.terrakok.cicerone.Command

interface WeatherFeatureStarter {

    fun start(): Command
}
