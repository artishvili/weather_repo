package com.devshish.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devshish.weather.api.WeatherFeatureStarter
import com.devshish.weather.app.WeatherApp
import com.devshish.weather.databinding.ActivityWeatherBinding
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class WeatherActivity : AppCompatActivity(R.layout.activity_weather) {

    @Inject
    lateinit var weatherFeatureStarter: WeatherFeatureStarter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = object : AppNavigator(this, R.id.containerMain) {
        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }

    private val binding by viewBinding(ActivityWeatherBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        WeatherApp.getComponent(this).inject(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            navigator.applyCommands(
                arrayOf(weatherFeatureStarter.start())
            )
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}
