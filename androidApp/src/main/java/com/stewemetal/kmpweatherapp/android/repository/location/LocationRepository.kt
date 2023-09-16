package com.stewemetal.kmpweatherapp.android.repository.location

import com.stewemetal.kmpweatherapp.android.repository.location.model.Coordinates
import com.stewemetal.kmpweatherapp.repository.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getLocation(): Location

    suspend fun getLocationUpdates(): Flow<Location?>
}
