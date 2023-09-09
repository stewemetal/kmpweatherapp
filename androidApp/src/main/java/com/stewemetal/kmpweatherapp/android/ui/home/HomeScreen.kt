package com.stewemetal.kmpweatherapp.android.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center,
    ) {
        val weather = state.weather
        if (weather != null) {
            Text(weather.sys.country)
        } else {
            Text("ERROR")
        }
    }
}
