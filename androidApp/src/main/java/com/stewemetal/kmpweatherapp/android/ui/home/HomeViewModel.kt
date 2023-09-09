package com.stewemetal.kmpweatherapp.android.ui.home

import androidx.lifecycle.viewModelScope
import com.stewemetal.kmpweatherapp.android.architecture.BaseViewModel
import com.stewemetal.kmpweatherapp.android.usecase.GetWeatherUseCase
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    val getWeatherUseCase: GetWeatherUseCase,
): BaseViewModel<HomeViewEvent, HomeState>(
    initialState = HomeState(),
) {

    init {
        viewModelScope.launch {
            val weatherResult = getWeatherUseCase.getWeather()
            emitNewState {
                copy(isLoading = false, weather = weatherResult)
            }
        }
    }

    override fun onViewEvent(event: HomeViewEvent) {
    }
}
