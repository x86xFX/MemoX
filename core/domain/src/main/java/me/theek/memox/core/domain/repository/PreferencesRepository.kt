package me.theek.memox.core.domain.repository

import kotlinx.coroutines.flow.Flow
import me.theek.memox.core.model.UserPreference

interface PreferencesRepository {
    val shouldHideOnboarding: Flow<UserPreference>
    suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean)
    suspend fun setShouldHideGetStartedScreen(shouldGetStartedScreen: Boolean)
}