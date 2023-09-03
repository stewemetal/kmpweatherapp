package com.stewemetal.kmpweatherapp.repository

import com.stewemetal.kmpweatherapp.network.OpenWeatherApi

class WeatherRepositoryImpl {
    private val weatherApi: OpenWeatherApi = OpenWeatherApi()

    @Throws(Exception::class)
    suspend fun getWeather() =
        weatherApi.getWeatherData(
            lat = 47.4523736,
            lon = 19.0381818,
        )
}
