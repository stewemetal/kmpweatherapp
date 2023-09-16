package com.stewemetal.kmpweatherapp.android.ui.home

sealed interface HomeViewEvent {
    data object LoadWeather: HomeViewEvent
}
