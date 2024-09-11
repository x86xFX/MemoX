package me.theek.memox.core.data.mappers

import me.theek.memox.core.database.NoteWithPhotosAndFolderDB
import me.theek.memox.core.model.NoteWithPhotosAndFolder

fun NoteWithPhotosAndFolderDB.toNoteWithPhotosAndFolder(): NoteWithPhotosAndFolder {
    return NoteWithPhotosAndFolder(
        note = note.toNote(),
        folder = folder.toFolder(),
        photo = photos?.map { it.toPhotoEntity() }
    )
}