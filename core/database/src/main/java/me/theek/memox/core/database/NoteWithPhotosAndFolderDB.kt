package me.theek.memox.core.database

import androidx.room.Embedded
import androidx.room.Relation
import me.theek.memox.core.database.entities.FolderEntity
import me.theek.memox.core.database.entities.NoteEntity
import me.theek.memox.core.database.entities.PhotoEntity

data class NoteWithPhotosAndFolderDB(
    @Embedded val note: NoteEntity,
    @Relation(
        parentColumn = "folder_id",
        entityColumn = "folder_id"
    )
    val folder: FolderEntity,
    @Relation(
        parentColumn = "note_id",
        entityColumn = "note_id"
    )
    val photos: List<PhotoEntity>?
)
