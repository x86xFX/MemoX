package me.theek.memox.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.theek.memox.core.data.repository.AdditionalFeaturesRepositoryImpl
import me.theek.memox.core.data.repository.DatastorePreferencesRepository
import me.theek.memox.core.data.repository.LocalFolderRepository
import me.theek.memox.core.data.repository.NoteRepositoryImpl
import me.theek.memox.core.domain.repository.AdditionalFeaturesRepository
import me.theek.memox.core.domain.repository.FolderRepository
import me.theek.memox.core.domain.repository.NoteRepository
import me.theek.memox.core.domain.repository.PreferencesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindings {

    @Binds
    @Singleton
    abstract fun bindFolderRepository(localFolderRepository: LocalFolderRepository): FolderRepository

    @Binds
    @Singleton
    abstract fun bindCameraRepository(cameraRepositoryImpl: AdditionalFeaturesRepositoryImpl): AdditionalFeaturesRepository

    @Binds
    @Singleton
    abstract fun bindPreferencesRepository(datastorePreferencesRepository: DatastorePreferencesRepository): PreferencesRepository

    @Binds
    @Singleton
    abstract fun bindNoteRepository(noteRepositoryImpl: NoteRepositoryImpl): NoteRepository
}