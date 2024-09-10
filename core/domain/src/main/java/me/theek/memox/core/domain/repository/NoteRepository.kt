package me.theek.memox.core.domain.repository

import kotlinx.coroutines.flow.Flow
import me.theek.memox.core.model.Note
import me.theek.memox.core.model.Photo

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun createNote(note: Note, pics: List<Photo>?)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun deleteNotes(vararg notes: Note)
}