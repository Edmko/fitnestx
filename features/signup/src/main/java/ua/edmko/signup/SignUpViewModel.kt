package ua.edmko.signup

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.edmko.core.BaseViewModel
import ua.edmko.core_ui.controllers.SnackbarMessage
import ua.edmko.core_ui.controllers.UserMessage

internal class SignUpViewModel : BaseViewModel<SignUpViewState, SignUpEvent>(SignUpViewState()) {

    override fun obtainEvent(viewEvent: SignUpEvent) {
        when (viewEvent) {
            is EmailChanges -> viewState = viewState.copy(email = viewEvent.value)
            is FirstNameChanges -> viewState = viewState.copy(firstName = viewEvent.value)
            is LastNameChanges -> viewState = viewState.copy(lastName = viewEvent.value)
            is PasswordChanges -> viewState = viewState.copy(password = viewEvent.value)
            is PrivacyCheckChanges -> viewState = viewState.copy(isPrivacyAccepted = viewEvent.value)
            FacebookClicks -> registerViaFacebook()
            GoogleClicks -> registerViaGoogle()
            LoginClicks -> navigateToLogin()
            RegisterClicks -> register()
            PrivacyClicks -> openPrivacy()
            TermsClicks -> openTerms()
            DismissSnackbar -> viewState = viewState.copy(snackbarMessage = null)
        }
    }

    private fun registerViaFacebook() = viewModelScope.launch {
        sendMessage("Not implemented yet")
    }

    private fun registerViaGoogle() = viewModelScope.launch {
        sendMessage("Not implemented yet")
    }

    private fun navigateToLogin() = viewModelScope.launch {
        sendMessage("Not implemented yet")
    }

    private fun register() = viewModelScope.launch {
        sendMessage("Not implemented yet")
    }

    private fun openPrivacy() = viewModelScope.launch {
        sendMessage("Not implemented yet")
    }

    private fun openTerms() = viewModelScope.launch {
        sendMessage("Not implemented yet")
    }

    private fun sendMessage(message: String) {
        viewState = viewState.copy(snackbarMessage = SnackbarMessage.from(UserMessage.from(message)))
    }
}