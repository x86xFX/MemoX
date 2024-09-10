package me.theek.memox.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import me.theek.memox.core.datastore_proto.OnboardingPreferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideOnboardingPreferencesDataStore(
        @ApplicationContext context: Context,
        onboardingPreferencesSerializer: OnboardingPreferencesSerializer
    ): DataStore<OnboardingPreferences> {

        return DataStoreFactory.create(
            serializer = onboardingPreferencesSerializer,
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { context.dataStoreFile(fileName = "onboarding_preferences.pb") }
        )

    }
}