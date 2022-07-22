package com.devshish.settings.impl.di.component

import com.devshish.settings.api.SettingsFeatureAPI
import com.devshish.settings.api.SettingsFeatureStarter
import com.devshish.settings.impl.di.deps.SettingsFeatureDependencies
import com.devshish.settings.impl.di.module.SettingsFeatureBindableModule
import com.devshish.settings.impl.di.module.SettingsFeatureViewModelModule
import com.devshish.settings.impl.presentation.settings.SettingsFragment
import dagger.Component
import javax.inject.Scope

@Scope
internal annotation class SettingsFeatureScope

@Component(
    modules = [
        SettingsFeatureBindableModule::class,
        SettingsFeatureViewModelModule::class
    ],
    dependencies = [SettingsFeatureDependencies::class]
)
@SettingsFeatureScope
internal interface SettingsFeatureComponent : SettingsFeatureAPI {

    override val settingsFeatureStarter: SettingsFeatureStarter

    /** Entry point of Settings feature */
    fun inject(fragment: SettingsFragment)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: SettingsFeatureDependencies): Builder

        fun build(): SettingsFeatureComponent
    }

    companion object {
        fun initAndGet(dependencies: SettingsFeatureDependencies): SettingsFeatureComponent {
            return DaggerSettingsFeatureComponent.builder()
                .dependencies(dependencies)
                .build()
        }
    }
}
