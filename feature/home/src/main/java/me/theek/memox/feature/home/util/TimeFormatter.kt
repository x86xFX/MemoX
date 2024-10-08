package me.theek.memox.feature.home.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.toReadableTime(): String {
    val formatter = DateTimeFormatter.ofPattern("MMM dd, HH:mm")
    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .format(formatter)
}