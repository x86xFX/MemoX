package me.theek.memox.navigation

import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.theek.memox.core.model.UserPreference
import me.theek.memox.core.util.UiState
import me.theek.memox.feature.home.GetStartedScreen
import me.theek.memox.feature.home.HomeScreen
import me.theek.memox.feature.note.NoteCreationScreen
import me.theek.memox.feature.onboarding.OnboardingScreen

@Composable
fun AppNavigator(
    onCurrentLocationClick: () -> Unit,
    uiState: UiState.Success<UserPreference>
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = uiState.calculateStartDestination()
    ) {
        composable(
            route = AppRoutes.ONBOARDING_SCREEN,
            exitTransition = { slideOutHorizontally() }
        ) {
            OnboardingScreen(
                onNavigateToHome = {
                    if (navController.canGoBack) {
                        navController.navigate(AppRoutes.GET_STARTED_SCREEN) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    }
                }
            )
        }

        composable(route = AppRoutes.HOME_SCREEN) {
            HomeScreen(
                onNavigateToNoteScreen = {
                    if (navController.canGoBack) {
                        navController.navigate(AppRoutes.NOTE_SCREEN)
                    }
                },
                onNavigateToEmptyScreen = {
                    if (navController.canGoBack) {
                        navController.navigate(AppRoutes.GET_STARTED_SCREEN)
                    }
                }
            )
        }

        composable(
            route = AppRoutes.NOTE_SCREEN,
            enterTransition = { scaleIn() },
            exitTransition = { scaleOut() }
        ) {
            NoteCreationScreen(
                onNavigateBack = {
                    if (navController.canGoBack) {
                        navController.navigate(AppRoutes.HOME_SCREEN) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    }
                },
                onCurrentLocationClick = onCurrentLocationClick
            )
        }

        composable(route = AppRoutes.GET_STARTED_SCREEN) {
            GetStartedScreen(
                onNoteCreateClicked = {
                    if (navController.canGoBack) {
                        navController.navigate(AppRoutes.NOTE_SCREEN)
                    }
                }
            )
        }
    }
}


private fun UiState.Success<UserPreference>.calculateStartDestination(): String {
    return when {
        !data.shouldHideOnboarding -> AppRoutes.ONBOARDING_SCREEN
        data.shouldHideGetStartedScreen -> AppRoutes.HOME_SCREEN
        else -> AppRoutes.GET_STARTED_SCREEN
    }
}

private val NavController.canGoBack: Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

object AppRoutes {
    const val ONBOARDING_SCREEN = "onboarding_screen"
    const val HOME_SCREEN = "home_screen"
    const val NOTE_SCREEN = "note_screen"
    const val GET_STARTED_SCREEN = "get_started_screen"
}