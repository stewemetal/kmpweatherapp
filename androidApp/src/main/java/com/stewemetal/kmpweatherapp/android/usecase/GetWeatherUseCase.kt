package com.stewemetal.kmpweatherapp.android.usecase

import com.stewemetal.kmpweatherapp.android.architecture.DispatcherProvider
import com.stewemetal.kmpweatherapp.network.model.WeatherResponse
import com.stewemetal.kmpweatherapp.repository.WeatherRepository
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    private val dispatcherProvider: DispatcherProvider,
) {

    suspend fun getWeather(): WeatherResponse =
        withContext(dispatcherProvider.io) {
            weatherRepository.getWeather()
        }
}
