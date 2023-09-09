package com.stewemetal.kmpweatherapp.repository

import com.stewemetal.kmpweatherapp.network.model.WeatherResponse

interface WeatherRepository {
    @Throws(Exception::class)
    suspend fun getWeather(): WeatherResponse
}
