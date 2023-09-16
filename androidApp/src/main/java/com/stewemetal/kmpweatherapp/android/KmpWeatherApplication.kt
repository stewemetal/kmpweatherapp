package com.stewemetal.kmpweatherapp.android

import android.app.Application
import com.stewemetal.kmpweatherapp.SharedModule
import com.stewemetal.kmpweatherapp.android.repository.RepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class KmpWeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KmpWeatherApplication)
            modules(
                AppModule().module,
                SharedModule().module,
                RepositoryModule().module,
            )
        }
    }
}
