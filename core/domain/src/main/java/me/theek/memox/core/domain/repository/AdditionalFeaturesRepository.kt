package me.theek.memox.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface AdditionalFeaturesRepository {
    fun checkCameraPermission(): Flow<Boolean>
    fun checkLocationPermission(): Flow<Boolean>
}