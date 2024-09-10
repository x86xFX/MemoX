package me.theek.memox.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val path: String,
    @ColumnInfo(name = "note_id") val noteId: Long,
    @ColumnInfo(name = "captured_at") val capturedAt: Long
)
