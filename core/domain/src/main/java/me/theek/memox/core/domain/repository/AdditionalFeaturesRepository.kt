package me.theek.memox.core.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import me.theek.memox.core.util.LocationState

interface AdditionalFeaturesRepository {
    val locationStream: StateFlow<LocationState>
    fun checkCameraPermission(): Flow<Boolean>
    fun checkLocationPermission(): Flow<Boolean>
}