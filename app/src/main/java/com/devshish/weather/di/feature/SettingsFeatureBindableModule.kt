package com.devshish.weather.di.feature

import com.devshish.settings.api.SettingsFeatureStarter
import com.devshish.settings.impl.presentation.SettingsFeatureStarterImpl
import com.devshish.weather.di.component.AppScope
import dagger.Binds
import dagger.Module

@Module
interface SettingsFeatureBindableModule {

    @[Binds AppScope]
    fun bindSettingsFeatureStarterImpl(
        starter: SettingsFeatureStarterImpl
    ): SettingsFeatureStarter
}
