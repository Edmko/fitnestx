package ua.edmko.core_ui.models

import androidx.compose.runtime.Stable

@Stable
data class InputModel(
    val value: String = "",
    val errorMessage: TextWrapper? = null,
    val maxLength: Int = Int.MAX_VALUE,
)