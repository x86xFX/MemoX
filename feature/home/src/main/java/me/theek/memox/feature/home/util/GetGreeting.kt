package me.theek.memox.feature.home.util

import android.content.Context
import android.icu.util.Calendar
import me.theek.memox.feature.home.R

fun Context.getGreeting(): String {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return when (currentHour) {
        in 0..11 -> {
            this.getString(R.string.good_morning)
        }
        in 12..17 -> {
            this.getString(R.string.good_afternoon)
        }
        else -> {
            this.getString(R.string.good_evening)
        }
    }
}