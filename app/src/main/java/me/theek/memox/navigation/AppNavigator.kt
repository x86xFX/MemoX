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
import me.theek.memox.UiState
import me.theek.memox.feature.home.HomeScreen
import me.theek.memox.feature.note.NoteCreationScreen
import me.theek.memox.feature.onboarding.OnboardingScreen

@Composable
fun AppNavigator(uiState: UiState.Success<Boolean>) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (uiState.data) AppRoutes.HOME_SCREEN else AppRoutes.ONBOARDING_SCREEN
    ) {
        composable(
            route = AppRoutes.ONBOARDING_SCREEN,
            exitTransition = { slideOutHorizontally() }
        ) {
            OnboardingScreen(
                onNavigateToHome = {
                    if (navController.canGoBack) {
                        navController.navigate(AppRoutes.HOME_SCREEN) {
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
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}

private val NavController.canGoBack: Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

object AppRoutes {
    const val ONBOARDING_SCREEN = "onboarding_screen"
    const val HOME_SCREEN = "home_screen"
    const val NOTE_SCREEN = "note_screen"
}