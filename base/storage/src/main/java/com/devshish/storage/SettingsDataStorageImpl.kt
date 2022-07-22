package com.devshish.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SettingsDataStorageImpl constructor(
    private val dataStore: DataStore<Preferences>
) : SettingsDataStorage {

    override val settingsDataUpdates: Flow<SettingsDataStorage.SettingsData?> = dataStore.data
        .map { prefs -> mapToSettingsData(prefs) }

    override suspend fun getSettingsData(): SettingsDataStorage.SettingsData? {
        return settingsDataUpdates.first()
    }

    override suspend fun setSettingsData(data: SettingsDataStorage.SettingsData) {
        dataStore.edit { prefs ->
            prefs[LOCATION] = data.location
            prefs[UNITS] = data.units
        }
    }

    override suspend fun clear() {
        dataStore.edit { prefs ->
            prefs.remove(LOCATION)
            prefs.remove(UNITS)
        }
    }

    private fun mapToSettingsData(prefs: Preferences): SettingsDataStorage.SettingsData? {
        val location = prefs[LOCATION]
        val units = prefs[UNITS]

        return if (location == null || units == null) {
            null
        } else {
            SettingsDataStorage.SettingsData(
                location = location,
                units = units
            )
        }
    }

    companion object {
        private val LOCATION: Preferences.Key<String> = stringPreferencesKey("location")
        private val UNITS: Preferences.Key<String> = stringPreferencesKey("units")
    }
}
