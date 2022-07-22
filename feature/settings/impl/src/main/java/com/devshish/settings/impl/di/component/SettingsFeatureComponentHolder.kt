package com.devshish.settings.impl.di.component

import androidx.lifecycle.ViewModel
import com.devshish.injector.ComponentHolder
import com.devshish.settings.api.SettingsFeatureAPI
import com.devshish.settings.impl.di.deps.SettingsFeatureDependencies
import com.devshish.settings.impl.di.deps.SettingsFeatureDependenciesStore

internal class SettingsFeatureComponentHolder :
    ViewModel(),
    ComponentHolder<SettingsFeatureAPI, SettingsFeatureDependencies> {

    private val component: SettingsFeatureComponent = SettingsFeatureComponent.initAndGet(
        SettingsFeatureDependenciesStore.dependencies
    )

    fun getComponent(): SettingsFeatureComponent = component

    override fun get(): SettingsFeatureAPI = getComponent()
}
