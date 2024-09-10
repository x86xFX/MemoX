package me.theek.memox.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.theek.memox.core.database.dao.FolderDao
import me.theek.memox.core.database.dao.NoteDao
import me.theek.memox.core.database.entities.FolderEntity
import me.theek.memox.core.database.entities.NoteEntity
import me.theek.memox.core.database.entities.PhotoEntity

@Database(
    entities = [FolderEntity::class, NoteEntity::class, PhotoEntity::class],
    version = 1,
    exportSchema = true
)
abstract class MemoXDatabase : RoomDatabase() {
    abstract fun folderDao() : FolderDao
    abstract fun noteDao() : NoteDao
}