package me.theek.memox.core.model

data class Folder(
    val id: Long = 0,
    val name: String,
    val modifiedDate: Long,
    val createdDate: Long
)
