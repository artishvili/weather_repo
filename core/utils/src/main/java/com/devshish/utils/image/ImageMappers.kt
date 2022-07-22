package com.devshish.utils.image

// TODO: think of a better place
fun mapToWeatherPicture(pictureCode: String): Int {
    return when (pictureCode) {
        "partly-cloudy-day" -> com.devshish.ui.R.drawable.ic_partly_cloudy_day
        "partly-cloudy-night" -> com.devshish.ui.R.drawable.ic_partly_cloudy_night
        "clear-day" -> com.devshish.ui.R.drawable.ic_clear_day
        "clear-night" -> com.devshish.ui.R.drawable.ic_clear_night
        "rain" -> com.devshish.ui.R.drawable.ic_rain
        "fog" -> com.devshish.ui.R.drawable.ic_fog
        "wind" -> com.devshish.ui.R.drawable.ic_wind
        "cloudy" -> com.devshish.ui.R.drawable.ic_cloudy
        "snow" -> com.devshish.ui.R.drawable.ic_snowd
        else -> error("Unsupported image code")
    }
}
