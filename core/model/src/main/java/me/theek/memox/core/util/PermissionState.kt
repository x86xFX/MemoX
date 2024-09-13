package me.theek.memox.core.util

interface PermissionState {
    data object Loading : PermissionState
    data object PermissionGranted : PermissionState
    data object PermissionDenied : PermissionState
}