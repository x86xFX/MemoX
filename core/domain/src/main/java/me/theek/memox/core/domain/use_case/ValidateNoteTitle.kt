package me.theek.memox.core.domain.use_case

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import me.theek.memox.core.domain.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateNoteTitle @Inject constructor(@ApplicationContext private val context: Context) {

    operator fun invoke(noteTitle: String) : ValidationResult {
        return when {
            noteTitle.isBlank() -> {
                ValidationResult(
                    isValid = false,
                    errorMessage = context.getString(R.string.title_cannot_be_empty)
                )
            }

            noteTitle.length > 50 -> {
                ValidationResult(
                    isValid = false,
                    errorMessage = context.getString(R.string.title_is_too_long)
                )
            }

            else -> {
                ValidationResult(isValid = true)
            }
        }
    }
}