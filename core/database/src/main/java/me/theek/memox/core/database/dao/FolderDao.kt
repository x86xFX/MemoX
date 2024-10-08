package me.theek.memox.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import me.theek.memox.core.database.entities.FolderEntity

@Dao
interface FolderDao {

    @Query("SELECT * FROM folders ORDER BY folder_modified_date DESC")
    fun getAllFolders(): Flow<List<FolderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createFolder(folderEntity: FolderEntity)

    @Query("UPDATE folders SET folder_name = :folderName, folder_modified_date = :modifiedDate, folder_created_date = :createdDate WHERE folder_id = :id")
    suspend fun updateFolder(id: Long, folderName: String, createdDate: Long, modifiedDate: Long)

    @Query("DELETE FROM folders WHERE folder_id = :id")
    suspend fun deleteFolder(id: Long)

    @Query("DELETE FROM notes WHERE folder_id = :id")
    suspend fun deleteFolderLinkedNotes(id: Long)

    @Transaction
    suspend fun deleteNotesAndFolder(folderId: Long) {
        deleteFolderLinkedNotes(id = folderId)
        deleteFolder(id = folderId)
    }
}