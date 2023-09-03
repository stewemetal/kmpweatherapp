package com.stewemetal.kmpweatherapp.network

import com.stewemetal.kmpweatherapp.network.model.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class OpenWeatherApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                }
            )
        }
    }

    suspend fun getWeatherData(
        lat: Double,
        lon: Double,
        apiKey: String = "97c1aa04a1d56123dcb7ca6b51732c85",
    ): WeatherResponse =
        httpClient
            .get("https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=$apiKey")
            .body()
}
