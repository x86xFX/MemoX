package me.theek.memox.core.domain.repository

import kotlinx.coroutines.flow.Flow
import me.theek.memox.core.model.LocationDetails

interface AdditionalFeaturesRepository {
    fun checkCameraPermission(): Flow<Boolean>
    fun checkLocationPermission(): Flow<Boolean>
    suspend fun requestLocationUpdates(): LocationDetails?
}