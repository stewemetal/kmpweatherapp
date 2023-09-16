package com.stewemetal.kmpweatherapp.repository

import com.stewemetal.kmpweatherapp.network.OpenWeatherApi
import com.stewemetal.kmpweatherapp.network.model.WeatherResponse
import com.stewemetal.kmpweatherapp.repository.model.Location
import org.koin.core.annotation.Singleton

@Singleton
internal class WeatherRepositoryImpl(
    private val weatherApi: OpenWeatherApi,
) : WeatherRepository {
    @Throws(Exception::class)
    override suspend fun getWeather(
        location: Location,
    ): WeatherResponse =
        weatherApi.getWeatherData(
            lat = 47.4523736,
            lon = 19.0381818,
        )
}
