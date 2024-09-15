package me.theek.memox.core.model

data class LocationDetails(
    val latitude: Double,
    val longitude: Double,
    val provider: String?,
    val accuracy: Float,
    val altitude: Double
)
