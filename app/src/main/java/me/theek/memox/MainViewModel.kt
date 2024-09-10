package me.theek.memox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import me.theek.memox.core.datastore.UserPreferencesDatasource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(userPreferencesDatasource: UserPreferencesDatasource) : ViewModel() {

    val uiState: StateFlow<UiState> = userPreferencesDatasource.shouldHideOnboarding
        .map {
            UiState.Success(data = it)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )
}

sealed interface UiState {
    data object Loading : UiState
    data class Success<T>(val data: T) : UiState
}