package me.theek.memox.core.domain.use_case

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)
