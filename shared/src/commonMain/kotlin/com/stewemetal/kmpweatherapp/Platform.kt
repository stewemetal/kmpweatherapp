package com.stewemetal.kmpweatherapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform