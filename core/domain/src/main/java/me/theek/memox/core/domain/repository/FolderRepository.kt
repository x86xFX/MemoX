package me.theek.memox.core.domain.repository

import kotlinx.coroutines.flow.Flow
import me.theek.memox.core.model.Folder

interface FolderRepository {
    fun getAllFolders(): Flow<List<Folder>>
    suspend fun createNewFolder(folder: Folder)
    suspend fun updateFolder(folder: Folder)
    suspend fun deleteFolder(folder: Folder)
}