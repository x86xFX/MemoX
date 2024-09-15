package me.theek.memox.core.data.service

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.os.Build
import android.os.CancellationSignal
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import me.theek.memox.core.model.LocationDetails

class LocationService(
    private val locationManager: LocationManager,
    private val context: Context
) {

    private var locationDetails: LocationDetails? = null

    suspend fun requestLocationUpdate(): LocationDetails? {
        getCurrentLocation()
        return locationDetails
    }

    @SuppressLint("MissingPermission")
    private suspend fun getCurrentLocation() {
        withContext(Dispatchers.IO) {
            delay(5000)
            if (isGpsProviderEnabled || isNetworkProviderEnabled) {
                if (isGpsProviderEnabled) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        getCurrentLocationForHigherAPIDevices(LocationManager.GPS_PROVIDER)
                    } else {
                        getCurrentLocationForLowerAPIDevices(LocationManager.GPS_PROVIDER)
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        getCurrentLocationForHigherAPIDevices(LocationManager.NETWORK_PROVIDER)
                    } else {
                        getCurrentLocationForLowerAPIDevices(LocationManager.NETWORK_PROVIDER)
                    }
                }
            } else {
                locationDetails = null
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("MissingPermission")
    private fun getCurrentLocationForHigherAPIDevices(provider: String) {
        locationManager.getCurrentLocation(
            provider,
            CancellationSignal(),
            ContextCompat.getMainExecutor(context)
        ) { location ->
            locationDetails = LocationDetails(
                latitude = location.latitude,
                longitude = location.longitude,
                accuracy = location.accuracy,
                provider = location.provider,
                altitude = location.altitude
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocationForLowerAPIDevices(provider: String) {
        LocationManagerCompat.getCurrentLocation(
            locationManager,
            provider,
            CancellationSignal(),
            ContextCompat.getMainExecutor(context)
        ) { location ->
            locationDetails = LocationDetails(
                latitude = location.latitude,
                longitude = location.longitude,
                accuracy = location.accuracy,
                provider = location.provider,
                altitude = location.altitude
            )
        }
    }

    private val isGpsProviderEnabled: Boolean
        get() = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    private val isNetworkProviderEnabled: Boolean
        get() = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
}
