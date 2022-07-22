package com.devshish.settings.impl.di.deps

import com.devshish.injector.DependenciesStore
import kotlin.properties.Delegates.notNull

object SettingsFeatureDependenciesStore : DependenciesStore<SettingsFeatureDependencies> {

    override var dependencies: SettingsFeatureDependencies by notNull()
}
