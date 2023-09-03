package com.stewemetal.kmpweatherapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stewemetal.kmpweatherapp.Greeting
import com.stewemetal.kmpweatherapp.network.model.WeatherResponse
import com.stewemetal.kmpweatherapp.repository.WeatherRepositoryImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var weatherData: WeatherResponse? by remember {
                        mutableStateOf(null)
                    }
                    val weatherRepository by remember {
                        mutableStateOf(WeatherRepositoryImpl())
                    }
                    LaunchedEffect(weatherData) {
                        if(weatherData == null) {
                            weatherData = weatherRepository.getWeather()
                        }
                    }

                    Column {
                        GreetingView(Greeting().greet())

                        val weather = weatherData
                        if(weather != null) {
//                        Text(text = weather.data[0].weather[0].description)
                            Text(text = weather.sys.country)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
