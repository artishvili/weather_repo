package com.devshish.weather.impl.presentation.weather

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devshish.mvvm.text.NativeText
import com.devshish.storage.SettingsDataStorage
import com.devshish.utils.result.doOnError
import com.devshish.utils.result.doOnSuccess
import com.devshish.weather.impl.BuildConfig
import com.devshish.weather.impl.domain.mapper.mapToUi
import com.devshish.weather.impl.domain.model.WeatherModel
import com.devshish.weather.impl.domain.usecase.FetchWeatherUseCase
import com.devshish.weather.impl.domain.usecase.FetchWeekForecastUseCase
import com.devshish.weather.impl.presentation.Screens
import com.devshish.weather.impl.presentation.weather.adapter.daily.UiDailyModel
import com.devshish.weather.impl.presentation.weather.adapter.hourly.UiWeatherHourlyModel
import com.github.terrakok.cicerone.Router
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class WeatherViewModel @Inject constructor(
    private val fetchWeatherUseCase: FetchWeatherUseCase,
    private val fetchWeekForecastUseCase: FetchWeekForecastUseCase,
    private val router: Router,
    private val settingsDataStorage: SettingsDataStorage
) : ViewModel() {

    val weather: Flow<WeatherModel>
        get() = _weather.asStateFlow().filterNotNull()
    private val _weather = MutableStateFlow<WeatherModel?>(null)

    val hourly: Flow<List<UiWeatherHourlyModel>>
        get() = _hourly.asStateFlow()
    private val _hourly = MutableStateFlow<List<UiWeatherHourlyModel>>(emptyList())

    val daily: Flow<List<UiDailyModel>>
        get() = _daily.asStateFlow()
    private val _daily = MutableStateFlow<List<UiDailyModel>>(emptyList())

    val error: Flow<NativeText>
        get() = _error.asSharedFlow()
    private val _error = MutableSharedFlow<NativeText>()

    val isRefreshing: Flow<Boolean>
        get() = _isRefreshing.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)

    val intent: Flow<Uri>
        get() = _intent.asSharedFlow()
    private val _intent = MutableSharedFlow<Uri>()

    init {
        refresh()
    }

    fun refresh() {
        with(viewModelScope) {
            launch {
                withContext(Dispatchers.Default) {
                    val settingsData = SettingsDataStorage.SettingsData(
                        location = DEFAULT_LOCATION,
                        units = DEFAULT_UNITS,
                    )
                    settingsDataStorage.setSettingsData(settingsData)
                }
            }

            launch {
                _isRefreshing.emit(true)

                val storedData = settingsDataStorage.getSettingsData()
                    ?: error("Stored data shouldn't be null")
                fetchWeatherUseCase.fetch(
                    location = storedData.location,
                    units = storedData.units
                )
                    .doOnSuccess {
                        _weather.emit(it)
                        _hourly.emit(
                            it.hourly.map { hour -> hour.mapToUi() }
                        )
                    }
                    .doOnError { _error.emit(it) }
            }

            launch {
                val storedData = settingsDataStorage.getSettingsData()
                    ?: error("Stored data shouldn't be null")
                fetchWeekForecastUseCase.fetch(
                    location = storedData.location,
                    units = storedData.units
                )
                    .doOnSuccess {
                        _daily.emit(
                            it.map { day -> day.mapToUi() }
                        )
                    }
                    .doOnError { _error.emit(it) }
                _isRefreshing.emit(false)
            }
        }
    }

    fun onMoreButtonClick() {
        router.navigateTo(Screens.forecastScreen())
    }

    fun onWeatherApiClick() {
        viewModelScope.launch { _intent.emit(BuildConfig.API_URL.toUri()) }
    }

    companion object {
        private const val DEFAULT_LOCATION = "Moscow"
        private const val DEFAULT_UNITS = "metric"
    }
}
