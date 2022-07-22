package com.devshish.weather.impl.presentation

import com.devshish.weather.api.WeatherFeatureStarter
import com.devshish.weather.impl.presentation.forecast.ForecastFragment
import com.devshish.weather.impl.presentation.weather.WeatherFragment
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class WeatherFeatureStarterImpl @Inject constructor(): WeatherFeatureStarter {

    override fun start(): Command {
        return Replace(Screens.weatherScreen())
    }
}

// TODO: move
object Screens {
    fun weatherScreen() = FragmentScreen { WeatherFragment.newInstance() }

    fun forecastScreen() = FragmentScreen { ForecastFragment.newInstance() }
}
