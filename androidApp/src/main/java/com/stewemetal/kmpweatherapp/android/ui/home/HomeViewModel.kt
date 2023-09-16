package com.stewemetal.kmpweatherapp.android.ui.home

import androidx.lifecycle.viewModelScope
import com.stewemetal.kmpweatherapp.android.architecture.BaseViewModel
import com.stewemetal.kmpweatherapp.android.ui.home.HomeViewEvent.LoadWeather
import com.stewemetal.kmpweatherapp.android.usecase.GetWeatherUseCase
import com.stewemetal.kmpweatherapp.network.model.WeatherResponse
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@KoinViewModel
class HomeViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
) : BaseViewModel<HomeViewEvent, HomeState>(
    initialState = HomeState(),
) {
    override fun onViewEvent(event: HomeViewEvent) {
        when (event) {
            LoadWeather -> {
                viewModelScope.launch {
                    val weatherResult = getWeatherUseCase.getWeather()
                    emitNewState {
                        copy(
                            isLoading = false,
                            weather = weatherResult.toPresentation(),
                        )
                    }
                }
            }
        }
    }

    private fun WeatherResponse.toPresentation() =
        WeatherPresentation(
            city = sys.country,
            date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")),
            imageUrl =
            "https://openweathermap.org/img/wn/${weather[0].icon}@2x.png",
            temperature = "${main.temp}°C",
            bottomInfoItems = buildList {
                add(
                    BottomInfoItem(
                        null,
                        "${wind.speed} km/h",
                        "Wind",
                    ),
                )
                add(
                    BottomInfoItem(
                        null,
                        "${main.humidity}%",
                        "Humidity",
                    ),
                )
                if (rain?.`1h` != null) {
                    add(
                        BottomInfoItem(
                            null,
                            "${rain?.`1h`}%",
                            "Rain",
                        ),
                    )
                }
            }
        )
}
