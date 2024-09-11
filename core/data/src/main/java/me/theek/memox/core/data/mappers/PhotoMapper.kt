package me.theek.memox.core.data.mappers

import android.net.Uri
import me.theek.memox.core.database.entities.PhotoEntity
import me.theek.memox.core.model.Photo

fun Photo.toPhotoEntity(noteId: Long): PhotoEntity {
    return PhotoEntity(
        path = path.toString(),
        noteId = noteId,
        capturedAt = capturedAt
    )
}

fun PhotoEntity.toPhotoEntity(): Photo {
    return Photo(
        path = Uri.parse(path),
        capturedAt = capturedAt
    )
}