package me.theek.memox.core.model

data class Note(
    val id: Long = 0,
    val title: String,
    val description: String,
    val folderId: Long,
    val modifiedDate: Long,
    val createdDate: Long
)
