package ua.edmko.onboarding

import ua.edmko.core.ViewState
import ua.edmko.core_ui.controllers.SnackbarMessage

data class OnboardingViewState(
    val snackbarMessage: SnackbarMessage? = null,
) : ViewState