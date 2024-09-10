package me.theek.memox.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    val shouldHideOnboarding: Flow<Boolean>
    suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean)
}