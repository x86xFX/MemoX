package me.theek.memox.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folders")
data class FolderEntity(
    @ColumnInfo(name = "folder_id") @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "folder_name") val folderName: String,
    @ColumnInfo(name = "folder_modified_date") val modifiedDate: Long,
    @ColumnInfo(name = "folder_created_date") val createdDate: Long
)