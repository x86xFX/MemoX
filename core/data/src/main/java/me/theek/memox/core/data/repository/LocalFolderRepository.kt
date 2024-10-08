package me.theek.memox.core.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import me.theek.memox.core.database.dao.FolderDao
import me.theek.memox.core.data.mappers.toFolder
import me.theek.memox.core.data.mappers.toFolderEntity
import me.theek.memox.core.domain.repository.FolderRepository
import me.theek.memox.core.model.Folder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalFolderRepository @Inject constructor(private val folderDao: FolderDao) : FolderRepository {

    override fun getAllFolders(): Flow<List<Folder>> {
        return folderDao
            .getAllFolders()
            .onStart { delay(4500) }
            .map { entities ->
                entities.map { it.toFolder() }
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun createNewFolder(folder: Folder) {
        withContext(Dispatchers.IO) {
            folderDao.createFolder(folderEntity = folder.toFolderEntity())
        }
    }

    override suspend fun updateFolder(folder: Folder) {
        withContext(Dispatchers.IO) {
            folderDao.updateFolder(
                id = folder.id,
                folderName = folder.name,
                modifiedDate = folder.modifiedDate,
                createdDate = folder.createdDate
            )
        }
    }

    override suspend fun deleteFolder(folder: Folder) {
        withContext(Dispatchers.IO) {
            folderDao.deleteNotesAndFolder(folderId = folder.id)
        }
    }
}