package me.theek.memox.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @ColumnInfo(name = "note_id") @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    @ColumnInfo(name = "folder_id") val folderId: Long,
    @ColumnInfo(name = "note_modified_date") val modifiedDate: Long,
    @ColumnInfo(name = "note_created_date") val createdDate: Long
)