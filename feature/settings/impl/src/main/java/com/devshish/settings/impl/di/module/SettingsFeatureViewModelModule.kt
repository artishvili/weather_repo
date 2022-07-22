package com.devshish.settings.impl.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devshish.mvvm.di.ViewModelFactory
import com.devshish.mvvm.di.ViewModelKey
import com.devshish.settings.impl.di.component.SettingsFeatureScope
import com.devshish.settings.impl.presentation.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface SettingsFeatureViewModelModule {

    @Binds
    @SettingsFeatureScope
    fun bindViewModelFactory(viewModel: ViewModelFactory): ViewModelProvider.Factory

    @[Binds IntoMap]
    @ViewModelKey(SettingsViewModel::class)
    fun bindWeatherViewModel(viewModel: SettingsViewModel): ViewModel
}
