package me.theek.memox.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface CameraRepository {
    fun checkCameraPermission(): Flow<Boolean>
}