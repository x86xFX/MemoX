package me.theek.memox.core.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import me.theek.memox.core.data.mappers.toNoteEntity
import me.theek.memox.core.data.mappers.toNoteWithPhotosAndFolder
import me.theek.memox.core.data.mappers.toPhotoEntity
import me.theek.memox.core.database.dao.NoteDao
import me.theek.memox.core.domain.repository.NoteRepository
import me.theek.memox.core.model.Note
import me.theek.memox.core.model.NoteWithPhotosAndFolder
import me.theek.memox.core.model.Photo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    override fun getAllNotes(): Flow<List<NoteWithPhotosAndFolder>> {
        return noteDao.getAllNotes()
            .onStart { delay(5000) }
            .map {
                it.map { noteWithPhotosAndFolderDB ->
                    noteWithPhotosAndFolderDB.toNoteWithPhotosAndFolder()
                }
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun createNote(note: Note, pics: List<Photo>?) {
        withContext(Dispatchers.IO) {
            val noteId = noteDao.createNote(noteEntity = note.toNoteEntity())
            if (pics != null) {
                noteDao.addPhotos(pics.map { it.toPhotoEntity(noteId) })
            }
        }
    }

    override suspend fun updateNote(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.updateNote(noteEntity = note.toNoteEntity())
        }
    }

    override suspend fun deleteNote(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.deleteNote(noteEntity = note.toNoteEntity())
        }
    }

    override suspend fun deleteNotes(vararg notes: Note) {
        withContext(Dispatchers.IO) {
            noteDao.deleteNotes(
                noteEntity = notes
                    .map { note ->
                        note.toNoteEntity()
                    }
                    .toTypedArray()
            )
        }
    }
}