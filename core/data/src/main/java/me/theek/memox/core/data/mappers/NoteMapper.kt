package me.theek.memox.core.data.mappers

import me.theek.memox.core.database.entities.NoteEntity
import me.theek.memox.core.model.Note

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        title = title,
        description = description,
        folderId = folderId,
        modifiedDate = modifiedDate,
        createdDate = createdDate
    )
}

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        description = description,
        folderId = folderId,
        modifiedDate = modifiedDate,
        createdDate = createdDate
    )
}