package me.theek.memox.core.data.repository

import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.theek.memox.core.data.mappers.toNoteEntity
import me.theek.memox.core.data.mappers.toNoteWithPhotosAndFolder
import me.theek.memox.core.data.mappers.toPhotoEntity
import me.theek.memox.core.database.dao.NoteDao
import me.theek.memox.core.domain.repository.NoteRepository
import me.theek.memox.core.domain.repository.PreferencesRepository
import me.theek.memox.core.model.Note
import me.theek.memox.core.model.NoteWithPhotosAndFolder
import me.theek.memox.core.model.Photo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val preferencesRepository: PreferencesRepository,
    @ApplicationContext private val context: Context
) : NoteRepository {

    override fun getAllNotes(): Flow<List<NoteWithPhotosAndFolder>> {
        return noteDao.getAllNotes()
            .onStart { delay(3000) }
            .map {
                it.map { noteWithPhotosAndFolderDB ->
                    noteWithPhotosAndFolderDB.toNoteWithPhotosAndFolder()
                }
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun createNote(note: Note, pics: List<Photo>?) {
        withContext(Dispatchers.IO) {
            launch {
                if (noteDao.isEmpty()) {
                    preferencesRepository.setShouldHideGetStartedScreen(true)
                }
            }

            launch {
                val noteId = noteDao.createNote(noteEntity = note.toNoteEntity())
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION

                if (pics != null) {
                    noteDao.addPhotos(
                        pics.map { pic ->
                            context.contentResolver.takePersistableUriPermission(pic.path, flag)
                            pic.toPhotoEntity(noteId)
                        }
                    )
                }
            }
        }
    }

    override suspend fun updateNote(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.updateNote(noteEntity = note.toNoteEntity())
        }
    }

    override suspend fun deleteNote(noteId: Long) {
        withContext(Dispatchers.IO) {
            noteDao.deleteNote(noteId = noteId)
        }
    }
}