package me.theek.memox.core.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.theek.memox.core.datastore_proto.OnboardingPreferences
import me.theek.memox.core.datastore_proto.copy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesDatasource @Inject constructor(private val onboardingPreferences: DataStore<OnboardingPreferences>) {

    val shouldHideOnboarding: Flow<Boolean> = onboardingPreferences
        .data
        .map {
            it.shouldHideOnboarding
        }

    suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean) {
        onboardingPreferences.updateData {
            it.copy {
                this.shouldHideOnboarding = shouldHideOnboarding
            }
        }
    }
}