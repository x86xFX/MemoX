package me.theek.memox.core.domain.use_case

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import me.theek.memox.core.domain.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateFolderName @Inject constructor(@ApplicationContext private val context: Context) {

    operator fun invoke(folderName: String): ValidationResult {
        return when {
            folderName.isBlank() -> ValidationResult(
                isValid = false,
                errorMessage = context.getString(R.string.folder_name_cannot_be_empty)
            )

            folderName.length > 10 -> ValidationResult(
                isValid = false,
                errorMessage = context.getString(R.string.folder_name_is_too_long)
            )

            else -> ValidationResult(isValid = true)
        }
    }
}