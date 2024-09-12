package me.theek.memox.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import me.theek.memox.core.database.NoteWithPhotosAndFolderDB
import me.theek.memox.core.database.entities.NoteEntity
import me.theek.memox.core.database.entities.PhotoEntity

@Dao
interface NoteDao {

    @Transaction
    @Query("SELECT * FROM notes ORDER BY note_modified_date DESC")
    fun getAllNotes(): Flow<List<NoteWithPhotosAndFolderDB>>

    @Query("SELECT (SELECT COUNT(*) FROM notes) == 0")
    fun isEmpty(): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNote(noteEntity: NoteEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPhotos(photoEntity: List<PhotoEntity>)

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNotes(vararg noteEntity: NoteEntity)
}