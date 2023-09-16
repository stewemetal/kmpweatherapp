package com.stewemetal.kmpweatherapp.android.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.stewemetal.kmpweatherapp.android.ui.home.HomeViewEvent.LoadWeather
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val locationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
    )

    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Center,
        ) {
            CircularProgressIndicator()
        }

        if (!locationPermissionState.status.isGranted) {
            SideEffect {
                locationPermissionState.launchPermissionRequest()
            }
        } else {
            viewModel.triggerViewEvent(LoadWeather)
        }
    } else {
        HomeScreenContent(state)
    }
}

@Composable
private fun HomeScreenContent(state: HomeState) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
    ) {
        val weather = state.weather
        if (weather != null) {
            Column(
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .weight(1f)
            ) {
                DateSection(
                    date = weather.date,
                )
                CitySection(weather.city)
                WeatherIllustration(
                    imageUrl = weather.imageUrl,
                    modifier = Modifier
                        .size(200.dp),
                )
                TemperatureSection(
                    temperature = weather.temperature,
                )
            }
            BottomInfoSection(
                items = weather.bottomInfoItems,
                modifier = Modifier
                    .padding(
                        bottom = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                    )
                    .fillMaxWidth()
                    .weight(1f),
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
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
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
    temperature: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = temperature,
        modifier = modifier,
    )
}

@Composable
fun BottomInfoSection(
    items: List<BottomInfoItem>,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Bottom,
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Green)
                .padding(8.dp),
        ) {
            items.forEachIndexed { index, item ->
                BottomInfoItem(
                    iconId = item.iconId,
                    text = item.dataText,
                    description = item.description,
                )
                if (index < items.lastIndex) {
                    VerticalDivider(
                        Modifier
                            .padding(vertical = 8.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun BottomInfoItem(
    text: String,
    description: String,
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int? = null,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally,
    ) {
        if (iconId != null) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp),
            )
        } else {
            Box(
                modifier = Modifier
                    .background(Color.Cyan)
                    .size(48.dp),
            )
        }
        Text(text)
        Text(description)
    }
}

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .fillMaxHeight()
            .width(1.dp)
            .background(color = Color.Blue)
    )
}

@Preview
@Composable
fun BottomInfoSectionPreview() {
    BottomInfoSection(
        items = listOf(
            BottomInfoItem(
                null,
                "24 km/h",
                "Wind",
            ),
            BottomInfoItem(
                null,
                "40%",
                "Humidity",
            ),
            BottomInfoItem(
                null,
                "0%",
                "Rain",
            ),
        )
    )
}
