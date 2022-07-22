package com.devshish.settings.impl.presentation.settings

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devshish.mvvm.util.observeWithLifecycle
import com.devshish.settings.impl.R
import com.devshish.settings.impl.databinding.FragmentSettingsBinding
import com.devshish.settings.impl.di.component.SettingsFeatureComponentHolder
import com.devshish.utils.imageview.loadDrawableRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: SettingsViewModel by viewModels { factory }

    private val binding by viewBinding(FragmentSettingsBinding::bind)

    private val featureComponentHolder: SettingsFeatureComponentHolder by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        featureComponentHolder.getComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            storage.observeWithLifecycle(viewLifecycleOwner) {
                binding.layoutLocation.tvItemValue.text = it.location
                binding.layoutUnits.tvItemValue.text = it.units.uppercase()
            }

            click.observeWithLifecycle(viewLifecycleOwner) {
                when (it) {
                    is SettingsViewModel.Click.OnLocationClick -> {
                        setupLocationDialog(it.location)
                    }
                    is SettingsViewModel.Click.OnUnitsClick -> {
                        setupUnitsDialog(it.unit)
                    }
                }
            }
        }

        with(binding) {
            ivBack.loadDrawableRes(com.devshish.ui.R.drawable.ic_back)
            layoutLocation.tvItem.text = getString(R.string.settings_location)
            layoutUnits.tvItem.text = getString(R.string.settings_units)

            layoutLocation.root.setOnClickListener { viewModel.onLocationClick() }
            layoutUnits.root.setOnClickListener { viewModel.onUnitsClick() }
            ivBack.setOnClickListener { viewModel.onBackButtonClick() }
        }
    }

    // TODO: use a different approach
    private fun setupLocationDialog(location: String) {
        val units = arrayOf(LOCATION_MOSCOW, LOCATION_MINSK)
        val checked = when (location) {
            LOCATION_MOSCOW -> LOCATION_MOSCOW_CODE
            LOCATION_MINSK -> LOCATION_MINSK_CODE
            else -> error("Unsupported action")
        }

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.settings_location)
            .setSingleChoiceItems(units, checked) { dialog, which ->
                viewModel.setLocation(
                    when (which) {
                        LOCATION_MOSCOW_CODE -> LOCATION_MOSCOW
                        LOCATION_MINSK_CODE -> LOCATION_MINSK
                        else -> error("Unsupported code")
                    }
                )
                dialog.dismiss()
            }
            .show()
    }

    private fun setupUnitsDialog(unit: String) {
        val units = arrayOf(UNITS_METRIC, UNITS_US)
        val checked = when (unit) {
            UNITS_METRIC.lowercase() -> UNITS_METRIC_CODE
            UNITS_US.lowercase() -> UNITS_US_CODE
            else -> error("Unsupported action")
        }

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.settings_units)
            .setSingleChoiceItems(units, checked) { dialog, which ->
                viewModel.setUnits(
                    when (which) {
                        UNITS_METRIC_CODE -> UNITS_METRIC.lowercase()
                        UNITS_US_CODE -> UNITS_US.lowercase()
                        else -> error("Unsupported code")
                    }
                )
                dialog.dismiss()
            }
            .show()
    }

    companion object {
        private const val UNITS_METRIC = "METRIC"
        private const val UNITS_US = "US"
        private const val UNITS_METRIC_CODE = 0
        private const val UNITS_US_CODE = 1

        private const val LOCATION_MOSCOW = "Moscow"
        private const val LOCATION_MINSK = "Minsk"
        private const val LOCATION_MOSCOW_CODE = 0
        private const val LOCATION_MINSK_CODE = 1

        fun newInstance(): SettingsFragment = SettingsFragment()
    }
}
