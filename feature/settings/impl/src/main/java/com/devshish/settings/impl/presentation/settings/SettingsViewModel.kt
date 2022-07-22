package com.devshish.settings.impl.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devshish.settings.impl.presentation.settings.SettingsViewModel.Click.OnLocationClick
import com.devshish.storage.SettingsDataStorage
import com.github.terrakok.cicerone.Router
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

internal class SettingsViewModel @Inject constructor(
    private val settingsDataStorage: SettingsDataStorage,
    private val router: Router
) : ViewModel() {

    val storage: Flow<SettingsDataStorage.SettingsData> = settingsDataStorage
        .settingsDataUpdates
        .filterNotNull()

    val click: Flow<Click>
        get() = _click.asSharedFlow()
    private val _click = MutableSharedFlow<Click>()

    fun onUnitsClick() {
        viewModelScope.launch {
            _click.emit(
                Click.OnUnitsClick(storage.first().units)
            )
        }
    }

    fun onBackButtonClick() {
        router.exit()
    }

    fun onLocationClick() {
        viewModelScope.launch {
            _click.emit(
                OnLocationClick(storage.first().location)
            )
        }
    }

    fun setUnits(units: String) {
        viewModelScope.launch {
            val settingsData = settingsDataStorage.getSettingsData()
                ?: error("Settings data shouldn't be null")
            settingsDataStorage.setSettingsData(
                settingsData.copy(units = units)
            )
        }
    }

    fun setLocation(location: String) {
        viewModelScope.launch {
            val settingsData = settingsDataStorage.getSettingsData()
                ?: error("Settings data shouldn't be null")
            settingsDataStorage.setSettingsData(
                settingsData.copy(location = location)
            )
        }
    }

    internal sealed class Click {
        data class OnLocationClick(val location: String) : Click()
        data class OnUnitsClick(val unit: String) : Click()
    }
}
