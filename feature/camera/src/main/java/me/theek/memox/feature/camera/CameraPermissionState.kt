package me.theek.memox.feature.camera

interface CameraPermissionState {
    data object Loading : CameraPermissionState
    data object PermissionGranted : CameraPermissionState
    data object PermissionDenied : CameraPermissionState
}