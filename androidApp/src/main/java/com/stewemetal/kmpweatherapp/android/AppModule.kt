package com.stewemetal.kmpweatherapp.android

import com.stewemetal.kmpweatherapp.android.architecture.DefaultDispatcherProvider
import com.stewemetal.kmpweatherapp.android.architecture.DispatcherProvider
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class AppModule {

    @Single
    fun dispatcherProvider(): DispatcherProvider =
        DefaultDispatcherProvider()
}
