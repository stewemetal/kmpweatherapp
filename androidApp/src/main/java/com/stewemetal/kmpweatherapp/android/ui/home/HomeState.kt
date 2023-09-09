package com.stewemetal.kmpweatherapp.android.ui.home

import com.stewemetal.kmpweatherapp.network.model.WeatherResponse

data class HomeState(
    val isLoading: Boolean = true,
    val weather: WeatherResponse? = null,
)
