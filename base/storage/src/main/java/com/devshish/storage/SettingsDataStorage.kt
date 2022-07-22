package com.devshish.storage

import kotlinx.coroutines.flow.Flow

interface SettingsDataStorage {

    data class SettingsData(
        val location: String,
        val units: String
    )

    val settingsDataUpdates: Flow<SettingsData?>

    suspend fun getSettingsData(): SettingsData?

    suspend fun setSettingsData(data: SettingsData)

    suspend fun clear()
}
