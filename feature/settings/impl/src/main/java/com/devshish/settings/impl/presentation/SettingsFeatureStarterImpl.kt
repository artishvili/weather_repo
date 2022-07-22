package com.devshish.settings.impl.presentation

import com.devshish.settings.api.SettingsFeatureStarter
import com.devshish.settings.impl.presentation.settings.SettingsFragment
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class SettingsFeatureStarterImpl @Inject constructor(
    private val router: Router
) : SettingsFeatureStarter {

    override fun start() {
        router.navigateTo(Screens.open())
    }
}

// TODO: move
object Screens {
    fun open(): FragmentScreen = FragmentScreen { SettingsFragment.newInstance() }
}
