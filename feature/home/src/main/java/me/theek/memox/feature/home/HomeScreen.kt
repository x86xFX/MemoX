package me.theek.memox.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.theek.memox.feature.home.components.EmptyNotesView

@Composable
fun HomeScreen(onNavigateToNoteScreen: () -> Unit) {
    EmptyNotesView(
        modifier = Modifier.fillMaxSize(),
        onNoteCreateClicked = onNavigateToNoteScreen
    )
}