package me.theek.memox.core.data.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.theek.memox.core.domain.repository.AdditionalFeaturesRepository
import javax.inject.Inject

class AdditionalFeaturesRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context) : AdditionalFeaturesRepository {

    override fun checkCameraPermission(): Flow<Boolean> = flow {
        delay(2000)
        if (context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            emit(true)
        } else {
            emit(false)
        }
    }

    override fun checkLocationPermission() = flow {
        delay(2000)
        if (
            context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            || context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
            ) {
            emit(true)
        } else {
            emit(false)
        }
    }
}