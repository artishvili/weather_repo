package com.devshish.weather.impl.presentation.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devshish.mvvm.text.NativeText
import com.devshish.storage.SettingsDataStorage
import com.devshish.utils.result.doOnError
import com.devshish.utils.result.doOnSuccess
import com.devshish.weather.impl.domain.mapper.mapToUi
import com.devshish.weather.impl.domain.usecase.FetchForecastUseCase
import com.devshish.weather.impl.presentation.forecast.adapter.UiForecastModel
import com.github.terrakok.cicerone.Router
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

internal class ForecastViewModel @Inject constructor(
    private val fetchForecastUseCase: FetchForecastUseCase,
    private val settingsDataStorage: SettingsDataStorage,
    private val router: Router
) : ViewModel() {

    val forecast: Flow<List<UiForecastModel>>
        get() = _forecast
    private val _forecast = MutableStateFlow<List<UiForecastModel>>(emptyList())

    val error: Flow<NativeText>
        get() = _error.asSharedFlow()
    private val _error = MutableSharedFlow<NativeText>()

    val isRefreshing: Flow<Boolean>
        get() = _isRefreshing.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            val settingsData = settingsDataStorage.getSettingsData()
                ?: error("Settings data shouldn't be null")

            fetchForecastUseCase.fetch(
                location = settingsData.location,
                units = settingsData.units
            )
                .doOnSuccess {
                    _forecast.emit(
                        it.map { day -> day.mapToUi() }
                    )
                }
                .doOnError { _error.emit(it) }
            _isRefreshing.emit(false)
        }
    }

    fun onBackButtonClick() {
        router.exit()
    }
}
