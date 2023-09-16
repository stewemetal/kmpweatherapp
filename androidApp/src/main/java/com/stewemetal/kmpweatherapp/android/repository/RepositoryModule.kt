package com.stewemetal.kmpweatherapp.android.repository

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.stewemetal.kmpweatherapp.android.repository.location.LocationRepository
import com.stewemetal.kmpweatherapp.android.repository.location.LocationRepositoryImpl
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton

@Module
@ComponentScan
class RepositoryModule {

    @Singleton
    fun locationRepository(
        context: Context,
    ): LocationRepository =
        LocationRepositoryImpl(
            LocationServices.getFusedLocationProviderClient(context)
        )
}
