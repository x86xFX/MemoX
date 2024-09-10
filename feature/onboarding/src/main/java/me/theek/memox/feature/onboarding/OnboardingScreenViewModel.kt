package me.theek.memox.feature.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.theek.memox.core.domain.repository.PreferencesRepository
import javax.inject.Inject

@HiltViewModel
class OnboardingScreenViewModel @Inject constructor(private val preferencesRepository: PreferencesRepository) : ViewModel() {

    var shouldNavigateToHome by mutableStateOf(false)
        private set

    fun onHideOnboardingScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setShouldHideOnboarding(shouldHideOnboarding = false)
        }
        shouldNavigateToHome = true
    }

}