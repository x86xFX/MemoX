package me.theek.memox.core.data.mappers

import me.theek.memox.core.database.entities.FolderEntity
import me.theek.memox.core.model.Folder

fun Folder.toFolderEntity() : FolderEntity {
    return FolderEntity(
        folderName = name,
        modifiedDate = modifiedDate,
        createdDate = createdDate
    )
}

fun FolderEntity.toFolder(): Folder {
    return Folder(
        id = id,
        name = folderName,
        modifiedDate = modifiedDate,
        createdDate = createdDate
    )
}