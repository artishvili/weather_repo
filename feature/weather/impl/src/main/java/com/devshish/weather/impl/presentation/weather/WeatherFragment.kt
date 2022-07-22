package com.devshish.weather.impl.presentation.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devshish.mvvm.text.toCharSequence
import com.devshish.mvvm.util.observeWithLifecycle
import com.devshish.recycler.data.DelegatesAdapter
import com.devshish.settings.api.SettingsFeatureStarter
import com.devshish.utils.date.CURRENT_BLOCK_DATE_PATTERN
import com.devshish.utils.date.formatDate
import com.devshish.utils.imageview.loadDrawableRes
import com.devshish.weather.impl.R
import com.devshish.weather.impl.databinding.FragmentWeatherBinding
import com.devshish.weather.impl.di.component.WeatherFeatureComponentHolder
import com.devshish.weather.impl.domain.model.WeatherModel
import com.devshish.weather.impl.presentation.ext.bind
import com.devshish.weather.impl.presentation.weather.adapter.daily.WeatherDailyAdapterDelegate
import com.devshish.weather.impl.presentation.weather.adapter.hourly.WeatherHourlyAdapterDelegate
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class WeatherFragment : Fragment(R.layout.fragment_weather) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var settingsFeatureStarter: SettingsFeatureStarter

    private val viewModel: WeatherViewModel by viewModels { factory }

    private val binding by viewBinding(FragmentWeatherBinding::bind)

    private val featureComponentHolder: WeatherFeatureComponentHolder by viewModels()

    private val hourlyAdapter = DelegatesAdapter(
        WeatherHourlyAdapterDelegate()
    )

    private val dailyAdapter = DelegatesAdapter(
        WeatherDailyAdapterDelegate()
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        featureComponentHolder.getComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            weather.observeWithLifecycle(viewLifecycleOwner) {
                bindWeather(it)
                bindWeatherInfo(it)
            }

            hourly.observeWithLifecycle(viewLifecycleOwner) {
                hourlyAdapter.submitList(it)
            }

            daily.observeWithLifecycle(viewLifecycleOwner) {
                dailyAdapter.submitList(it)
            }

            error.observeWithLifecycle(viewLifecycleOwner) {
                Snackbar.make(
                    requireView(),
                    it.toCharSequence(requireContext()),
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            isRefreshing.observeWithLifecycle(viewLifecycleOwner) {
                binding.swipeRefresh.isRefreshing = it
            }

            intent.observeWithLifecycle(viewLifecycleOwner) {
                startActivity(
                    Intent(Intent.ACTION_VIEW, it)
                )
            }
        }

        with(binding) {
            rvWeatherHourly.adapter = hourlyAdapter
            rvDayForecast.adapter = dailyAdapter

            btnMore.setOnClickListener { viewModel.onMoreButtonClick() }
            ivSettings.setOnClickListener { settingsFeatureStarter.start() }
            swipeRefresh.setOnRefreshListener { viewModel.refresh() }
            tvWeatherApi.setOnClickListener { viewModel.onWeatherApiClick() }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }

    private fun bindWeather(weather: WeatherModel) {
        with(binding) {
            tvLocation.text = weather.location
            tvCondition.text = weather.conditions
            tvDate.text = weather.date.formatDate(CURRENT_BLOCK_DATE_PATTERN)

            tvTemp.text = getString(
                R.string.weather_temp_now,
                weather.tempNow
            )
            tvTempDayNight.text = getString(
                R.string.weather_temp_max_min,
                weather.tempMax,
                weather.tempMin
            )
            tvFeelsLike.text = getString(
                R.string.weather_temp_feels_like,
                weather.tempFeelsLike
            )

            ivWeatherIcon.loadDrawableRes(weather.icon)
        }
    }

    private fun bindWeatherInfo(weather: WeatherModel) {
        with(binding) {
            layoutUV.bind(
                iconRes = com.devshish.ui.R.drawable.ic_uv_index,
                titleRes = R.string.weather_info_uv,
                value = weather.uvIndex
            )

            layoutSunrise.bind(
                iconRes = com.devshish.ui.R.drawable.ic_sunrise,
                titleRes = R.string.weather_info_sunrise,
                value = weather.sunrise
            )

            layoutSunset.bind(
                iconRes = com.devshish.ui.R.drawable.ic_sunset,
                titleRes = R.string.weather_info_sunset,
                value = weather.sunset
            )

            layoutWind.bind(
                iconRes = com.devshish.ui.R.drawable.ic_wind_info,
                titleRes = R.string.weather_info_wind,
                value = weather.windSpeed
            )

            layoutHumidity.bind(
                iconRes = com.devshish.ui.R.drawable.ic_humidity,
                titleRes = R.string.weather_info_humidity,
                value = weather.humidity
            )
        }
    }

    companion object {
        fun newInstance(): WeatherFragment = WeatherFragment()
    }
}
