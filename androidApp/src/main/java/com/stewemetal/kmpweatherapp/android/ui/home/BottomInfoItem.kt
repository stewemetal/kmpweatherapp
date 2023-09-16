package com.stewemetal.kmpweatherapp.android.ui.home

import androidx.annotation.DrawableRes

data class BottomInfoItem(
    @DrawableRes val iconId: Int?,
    val dataText: String,
    val description: String,
)
