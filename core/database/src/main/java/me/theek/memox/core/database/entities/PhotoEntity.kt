package me.theek.memox.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "photos",
    foreignKeys = [
        ForeignKey(
            entity = NoteEntity::class,
            parentColumns = arrayOf("note_id"),
            childColumns = arrayOf("note_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val path: String,
    @ColumnInfo(name = "note_id", index = true) val noteId: Long,
    @ColumnInfo(name = "captured_at") val capturedAt: Long
)
