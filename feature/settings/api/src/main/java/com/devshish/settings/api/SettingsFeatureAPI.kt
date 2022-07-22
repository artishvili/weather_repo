package com.devshish.settings.api

import com.devshish.injector.BaseFeatureAPI

interface SettingsFeatureAPI : BaseFeatureAPI {

    val settingsFeatureStarter: SettingsFeatureStarter
}
