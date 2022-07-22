package com.devshish.weather.impl.data.api

import com.devshish.weather.impl.data.model.WeatherResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface WeatherAPI {

    @GET("timeline/{location}")
    suspend fun fetchWeather(
        @Path("location") location: String,
        @Query("unitGroup") units: String
    ): Response<WeatherResponseModel>
}
