package com.stewemetal.kmpweatherapp.repository

import com.stewemetal.kmpweatherapp.network.model.WeatherResponse
import com.stewemetal.kmpweatherapp.repository.model.Location

interface WeatherRepository {
    @Throws(Exception::class)
    suspend fun getWeather(
        location: Location,
    ): WeatherResponse
}
