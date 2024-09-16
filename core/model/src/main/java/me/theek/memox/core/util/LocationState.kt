package me.theek.memox.core.util

import me.theek.memox.core.model.LocationDetails

sealed interface LocationState {
    data object Idle : LocationState
    data object Loading : LocationState
    data class Failure(val message: String?) : LocationState
    data class Success(val data: LocationDetails?) : LocationState
}