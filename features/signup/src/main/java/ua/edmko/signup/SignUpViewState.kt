package ua.edmko.signup

import ua.edmko.core.ViewState
import ua.edmko.core_ui.controllers.SnackbarMessage

data class SignUpViewState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val isPrivacyAccepted: Boolean = false,
    val snackbarMessage: SnackbarMessage? = null,
) : ViewState