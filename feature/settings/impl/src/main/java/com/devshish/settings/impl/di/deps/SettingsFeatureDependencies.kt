package com.devshish.settings.impl.di.deps

import com.devshish.injector.BaseFeatureDependencies
import com.devshish.storage.SettingsDataStorage
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class SettingsFeatureDependencies @Inject constructor(
    val settingsDataStorage: SettingsDataStorage,
    val router: Router
) : BaseFeatureDependencies
