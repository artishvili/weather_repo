package com.devshish.settings.impl.di.module

import com.devshish.settings.api.SettingsFeatureStarter
import com.devshish.settings.impl.presentation.SettingsFeatureStarterImpl
import dagger.Binds
import dagger.Module

@Module
internal interface SettingsFeatureBindableModule {

    @Binds
    fun bindSettingsFeatureStarterImpl(
        starter: SettingsFeatureStarterImpl
    ): SettingsFeatureStarter
}
