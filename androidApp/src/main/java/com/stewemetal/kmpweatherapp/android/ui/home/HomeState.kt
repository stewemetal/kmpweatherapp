package com.stewemetal.kmpweatherapp.android.ui.home

import com.stewemetal.kmpweatherapp.network.model.Weather


data class HomeState(
    val isLoading: Boolean = true,
    val weather: WeatherPresentation? = null,
)

data class WeatherPresentation(
    val city: String,
    val date: String,
    val imageUrl: String,
    val temperature: String,
    val bottomInfoItems: List<BottomInfoItem> = emptyList(),
)
