package com.stewemetal.kmpweatherapp.android.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        HomeScreenContent(state)
    }
}

@Composable
private fun HomeScreenContent(state: HomeState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val weather = state.weather
        if (weather != null) {
            Text(weather.sys.country)
            WeatherIllustration(
                iconId = weather.weather[0].icon,
                modifier = Modifier
                    .size(200.dp),
            )
            TemperatureSection(
                temperature = weather.main.temp,
            )
        } else {
            Text("ERROR")
        }
    }
}

@Composable
fun DateSection(
    date: String,
    modifier: Modifier = Modifier,
) {
    Text(text = date)
}

@Composable
fun CitySection(
    city: String,
    modifier: Modifier = Modifier,
) {
    Text(text = city)
}

@Composable
fun WeatherIllustration(
    iconId: String,
    modifier: Modifier = Modifier,
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://openweathermap.org/img/wn/$iconId@2x.png")
            .crossfade(true)
            .build(),
        loading = {
            CircularProgressIndicator(modifier = modifier)
        },
        error = {
            it.result.throwable.localizedMessage?.let { message ->
                Text(message)
            }
        },
        contentDescription = null,
        modifier = modifier,
    )
}

@Composable
fun TemperatureSection(
    temperature: Double,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "$temperature°C",
        modifier = modifier,
    )
}

