package me.theek.memox.core.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.theek.memox.core.data.mappers.toNote
import me.theek.memox.core.data.mappers.toNoteEntity
import me.theek.memox.core.data.mappers.toPhotoEntity
import me.theek.memox.core.database.dao.NoteDao
import me.theek.memox.core.domain.repository.NoteRepository
import me.theek.memox.core.model.Note
import me.theek.memox.core.model.Photo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map {
            it.map { noteWithFolder ->
                noteWithFolder.note.toNote()
            }
        }
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