package me.theek.memox.core.data.service

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.theek.memox.core.model.CurrentLocationDetails
import me.theek.memox.core.util.LocationState

class LocationService(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val context: Context
) {
    private val _locationState: MutableStateFlow<LocationState> = MutableStateFlow(LocationState.Idle)
    val locationState = _locationState.asStateFlow()

    private val cancellation = CancellationTokenSource()

    fun createLocationRequest(activity: Activity) {
        val locationRequest = LocationRequest
            .Builder(Priority.PRIORITY_BALANCED_POWER_ACCURACY, 1000)
            .build()

        val locationSettings = LocationSettingsRequest
            .Builder()
            .addLocationRequest(locationRequest)
            .build()

        val settingsClient = LocationServices.getSettingsClient(context)
        val settingsTask = settingsClient.checkLocationSettings(locationSettings)

        settingsTask
            .addOnSuccessListener {
                getLocation()
            }
            .addOnFailureListener {
                if (it is ResolvableApiException) {
                    try {
                        it.startResolutionForResult(activity, 1)
                    } catch (sendEx: IntentSender.SendIntentException) {
                        _locationState.value = LocationState.Failure(sendEx.localizedMessage)
                    } catch (e: Exception) {
                        _locationState.value = LocationState.Failure(e.localizedMessage)
                    }
                }
            }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {

        val locationTask = fusedLocationProviderClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            cancellation.token
        )

        locationTask
            .addOnSuccessListener { location ->
                _locationState.value = LocationState.Success(
                    CurrentLocationDetails(
                        latitude = location.latitude,
                        longitude = location.longitude,
                        provider = location.provider,
                        accuracy = location.accuracy,
                        altitude = location.altitude
                    )
                )
            }
            .addOnFailureListener { error ->
                _locationState.value = LocationState.Failure(error.localizedMessage)
            }
    }
}
