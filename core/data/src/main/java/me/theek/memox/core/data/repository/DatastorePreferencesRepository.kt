package me.theek.memox.core.data.repository

import kotlinx.coroutines.flow.Flow
import me.theek.memox.core.datastore.UserPreferencesDatasource
import me.theek.memox.core.domain.repository.PreferencesRepository
import me.theek.memox.core.model.UserPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatastorePreferencesRepository @Inject constructor(private val userPreferencesDatasource: UserPreferencesDatasource) : PreferencesRepository {

    override val shouldHideOnboarding: Flow<UserPreference> = userPreferencesDatasource.shouldHideOnboarding

    override suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean) {
        userPreferencesDatasource.setShouldHideOnboarding(shouldHideOnboarding)
    }

    override suspend fun setShouldHideGetStartedScreen(shouldGetStartedScreen: Boolean) {
        userPreferencesDatasource.setShouldHideGetStartedScreen(shouldGetStartedScreen)
    }
}