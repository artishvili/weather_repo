package com.devshish.weather.impl.presentation.forecast

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devshish.mvvm.text.toCharSequence
import com.devshish.mvvm.util.observeWithLifecycle
import com.devshish.recycler.data.DelegatesAdapter
import com.devshish.weather.impl.R
import com.devshish.weather.impl.databinding.FragmentForecastBinding
import com.devshish.weather.impl.di.component.WeatherFeatureComponentHolder
import com.devshish.weather.impl.presentation.forecast.adapter.ForecastAdapterDelegate
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ForecastFragment : Fragment(R.layout.fragment_forecast) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: ForecastViewModel by viewModels { factory }

    private val binding by viewBinding(FragmentForecastBinding::bind)

    private val forecastAdapter = DelegatesAdapter(
        ForecastAdapterDelegate()
    )

    private val featureComponentHolder: WeatherFeatureComponentHolder by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        featureComponentHolder.getComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            forecast.observeWithLifecycle(viewLifecycleOwner) {
                forecastAdapter.submitList(it)
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
        }

        with(binding) {
            rvForecast.adapter = forecastAdapter

            ivBack.setOnClickListener { viewModel.onBackButtonClick() }

            swipeRefresh.setOnRefreshListener { viewModel.refresh() }
        }
    }

    companion object {
        fun newInstance(): ForecastFragment = ForecastFragment()
    }
}
