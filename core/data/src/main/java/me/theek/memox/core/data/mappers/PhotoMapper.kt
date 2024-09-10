package me.theek.memox.core.data.mappers

import me.theek.memox.core.database.entities.PhotoEntity
import me.theek.memox.core.model.Photo

fun Photo.toPhotoEntity(noteId: Long): PhotoEntity {
    return PhotoEntity(
        path = path.toString(),
        noteId = noteId,
        capturedAt = capturedAt
    )
}