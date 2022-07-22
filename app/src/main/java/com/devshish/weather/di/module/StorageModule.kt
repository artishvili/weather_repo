package com.devshish.weather.di.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.devshish.storage.SettingsDataStorage
import com.devshish.storage.SettingsDataStorageImpl
import com.devshish.weather.di.component.AppScope
import dagger.Module
import dagger.Provides

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preferences")

@Module
internal class StorageModule {

    @[Provides AppScope]
    fun provideSettingsStorageImpl(dataStore: DataStore<Preferences>): SettingsDataStorage {
        return SettingsDataStorageImpl(dataStore)
    }

    @[Provides AppScope]
    fun provideDataSource(context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}
