package me.theek.memox.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import me.theek.memox.core.database.entities.FolderEntity

@Dao
interface FolderDao {

    @Query("SELECT * FROM folders ORDER BY folder_modified_date DESC")
    fun getAllFolders(): Flow<List<FolderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createFolder(folderEntity: FolderEntity)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun updateFolder(folderEntity: FolderEntity)

    @Delete
    suspend fun deleteFolder(folderEntity: FolderEntity)
}