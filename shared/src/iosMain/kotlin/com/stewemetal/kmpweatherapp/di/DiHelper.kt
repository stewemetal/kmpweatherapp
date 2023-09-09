package com.stewemetal.kmpweatherapp.di

import com.stewemetal.kmpweatherapp.sharedModule
import org.koin.core.context.startKoin

@Suppress("unused")
fun initKoin() {
    startKoin {
        modules(
            sharedModule,
        )
    }
}
