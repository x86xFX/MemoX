package me.theek.memox.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.theek.memox.core.database.MemoXDatabase
import me.theek.memox.core.database.dao.FolderDao
import me.theek.memox.core.database.dao.NoteDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMemoxDatabase(@ApplicationContext context: Context): MemoXDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = MemoXDatabase::class.java,
            name = "memox_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFolderDao(memoXDatabase: MemoXDatabase): FolderDao {
        return memoXDatabase.folderDao()
    }

    @Provides
    @Singleton
    fun provideNoteDao(memoXDatabase: MemoXDatabase): NoteDao {
        return memoXDatabase.noteDao()
    }
}