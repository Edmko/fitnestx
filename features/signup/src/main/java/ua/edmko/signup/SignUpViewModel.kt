package ua.edmko.signup

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.edmko.core.BaseViewModel
import ua.edmko.core_ui.models.validator.Invalid
import ua.edmko.core_ui.models.validator.isValid
import ua.edmko.core_ui.controllers.SnackbarMessage
import ua.edmko.core_ui.controllers.UserMessage
import ua.edmko.core_ui.models.InputModel
import ua.edmko.core_ui.models.validator.messageOrNull
import ua.edmko.domain.interactors.entities.UserRegisterData
import ua.edmko.domain.interactors.interactors.InvokeError
import ua.edmko.domain.interactors.interactors.InvokeStarted
import ua.edmko.domain.interactors.interactors.InvokeSuccess
import ua.edmko.domain.interactors.interactors.Register
import ua.edmko.signup.validators.EmailValidator
import ua.edmko.signup.validators.NameValidator
import ua.edmko.signup.validators.PasswordValidator
import javax.inject.Inject

@HiltViewModel
internal class SignUpViewModel @Inject constructor(
    private val register: Register,
    private val navigator: SignUpNavigator,
) : BaseViewModel<SignUpViewState, SignUpEvent>(SignUpViewState()) {

    private val nameValidator = NameValidator()
    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()

    override fun obtainEvent(viewEvent: SignUpEvent) {
        when (viewEvent) {
            is EmailChanges -> viewState = viewState.copy(email = viewState.email.copy(viewEvent.value, errorMessage = null))
            is FirstNameChanges -> viewState = viewState.copy(firstName = viewState.firstName.copy(viewEvent.value, errorMessage = null))
            is LastNameChanges -> viewState = viewState.copy(lastName = viewState.lastName.copy(viewEvent.value, errorMessage = null))
            is PasswordChanges -> viewState = viewState.copy(password = viewState.password.copy(viewEvent.value, errorMessage = null))
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
        val userRegisterData = UserRegisterData(
            firstName = viewState.firstName.value,
            lastName = viewState.lastName.value,
            email = viewState.email.value,
            password = viewState.password.value
        )
        if (validate(userRegisterData)) {
            register(Register.Params(userRegisterData)).collect {
                when (it) {
                    is InvokeError -> sendMessage(it.throwable.message ?: "Something went wrong")
                    InvokeStarted -> {}
                    InvokeSuccess -> navigator.toGoalSetup()
                }
            }
        }
    }

    private fun validate(data: UserRegisterData): Boolean {
        val nameValidation = nameValidator.validate(data.firstName)
        val lastNameValidation = nameValidator.validate(data.lastName)
        val emailValidation = emailValidator.validate(data.email)
        val passwordValidation = passwordValidator.validate(data.password)

        viewState = viewState.copy(
            firstName = viewState.firstName.copy(errorMessage = nameValidation.messageOrNull),
            lastName = viewState.lastName.copy(errorMessage = lastNameValidation.messageOrNull),
            email = viewState.email.copy(errorMessage = emailValidation.messageOrNull),
            password = viewState.password.copy(errorMessage = passwordValidation.messageOrNull),
        )
        return nameValidation.isValid() && lastNameValidation.isValid() && emailValidation.isValid() && passwordValidation.isValid()
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