package com.stewemetal.kmpweatherapp

import com.stewemetal.kmpweatherapp.repository.WeatherRepositoryImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("unused")
class WeatherRepository: KoinComponent {
    private val weatherRepositoryImpl: WeatherRepositoryImpl by inject()

    suspend fun getWeather() = 
        weatherRepositoryImpl.getWeather()
}
