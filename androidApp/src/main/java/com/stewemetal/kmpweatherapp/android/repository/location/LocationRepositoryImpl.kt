package com.stewemetal.kmpweatherapp.android.repository.location

import android.annotation.SuppressLint
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.stewemetal.kmpweatherapp.android.repository.location.model.Coordinates
import com.stewemetal.kmpweatherapp.repository.model.Location
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.koin.core.annotation.Singleton
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LocationRepositoryImpl(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
) : LocationRepository {
    @SuppressLint("MissingPermission")
    override suspend fun getLocation(): Location =
        suspendCoroutine { continuation ->
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        continuation.resume(Location(it.latitude, it.longitude))
                    } ?: run {
                        continuation.resumeWithException(Exception("Unable to get current location."))
                    }
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }

    @SuppressLint("MissingPermission")
    override suspend fun getLocationUpdates(): Flow<Location?> =
        callbackFlow {
            val locationRequest = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                TimeUnit.SECONDS.toMillis(10),
            ).build()

            val callBack = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    val location = locationResult.lastLocation

                    if (location != null) {
                        trySend(
                            Location(
                                lat = location.latitude,
                                lon = location.longitude,
                            )
                        )
                    } else {
                        trySend(null)
                    }
                }
            }

            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                callBack,
                Looper.getMainLooper()
            )

            awaitClose {
                fusedLocationProviderClient.removeLocationUpdates(callBack)
            }
        }
}
