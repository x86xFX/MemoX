package me.theek.memox.core.model

import android.net.Uri

data class Photo(
    val id: Long = 0,
    val path: Uri,
    val capturedAt: Long
)