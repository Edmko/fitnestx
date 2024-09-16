package ua.edmko.signup

import androidx.compose.runtime.Immutable
import ua.edmko.core.ViewState
import ua.edmko.core_ui.controllers.SnackbarMessage
import ua.edmko.core_ui.models.InputModel
import ua.edmko.signup.configs.NAME_MAX_LENGTH
import ua.edmko.signup.configs.PASSWORD_MAX_LENGTH

@Immutable
data class SignUpViewState(
    val firstName: InputModel = InputModel(maxLength = NAME_MAX_LENGTH),
    val lastName: InputModel = InputModel(maxLength = NAME_MAX_LENGTH),
    val email: InputModel = InputModel(),
    val password: InputModel = InputModel(maxLength = PASSWORD_MAX_LENGTH),
    val isPrivacyAccepted: Boolean = false,
    val snackbarMessage: SnackbarMessage? = null,
) : ViewState