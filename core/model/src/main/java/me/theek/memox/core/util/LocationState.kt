package me.theek.memox.core.util

import me.theek.memox.core.model.CurrentLocationDetails

sealed interface LocationState {
    data object Idle : LocationState
    data class Failure(val message: String?) : LocationState
    data class Success(val data: CurrentLocationDetails?) : LocationState
}