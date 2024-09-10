package me.theek.memox.core.model

data class LocationDetails(
    val provider: String,
    val latitude: Double,
    val longitude: Double,
    val accuracy: Float,
    val altitude: Double
)
