package com.devshish.weather.impl.di.deps

import android.content.Context
import com.devshish.injector.BaseFeatureDependencies
import com.devshish.settings.api.SettingsFeatureStarter
import com.devshish.storage.SettingsDataStorage
import com.github.terrakok.cicerone.Router
import javax.inject.Inject
import retrofit2.Retrofit

class WeatherFeatureDependencies @Inject constructor(
    val retrofit: Retrofit,
    val settingsDataStorage: SettingsDataStorage,
    val router: Router,
    val settingsFeatureStarter: SettingsFeatureStarter
) : BaseFeatureDependencies
