package me.theek.memox.core.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.theek.memox.core.datastore_proto.UserPreferences
import me.theek.memox.core.datastore_proto.copy
import me.theek.memox.core.model.UserPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesDatasource @Inject constructor(private val userPreferences: DataStore<UserPreferences>) {

    val shouldHideOnboarding: Flow<UserPreference> = userPreferences
        .data
        .map {
            UserPreference(
                shouldHideOnboarding = it.shouldHideOnboarding,
                shouldHideGetStartedScreen = it.shouldShowGetStartedScreen
            )
        }

    suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean) {
        userPreferences.updateData {
            it.copy {
                this.shouldHideOnboarding = shouldHideOnboarding
            }
        }
    }

    suspend fun setShouldHideGetStartedScreen(shouldGetStartedScreen: Boolean) {
        userPreferences.updateData {
            it.copy {
                this.shouldShowGetStartedScreen = shouldGetStartedScreen
            }
        }
    }
}