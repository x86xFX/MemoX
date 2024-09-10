package me.theek.memox.core.model

data class NoteWithPhotosAndFolder(
    val note: Note,
    val folder: Folder,
    val photo: List<Photo>?
)
