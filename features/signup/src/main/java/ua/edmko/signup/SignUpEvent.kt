package ua.edmko.signup

import ua.edmko.core.Event

sealed interface SignUpEvent : Event
data class FirstNameChanges(val value: String) : SignUpEvent
data class LastNameChanges(val value: String) : SignUpEvent
data class EmailChanges(val value: String) : SignUpEvent
data class PasswordChanges(val value: String) : SignUpEvent
data class PrivacyCheckChanges(val value: Boolean) : SignUpEvent
data object RegisterClicks : SignUpEvent
data object GoogleClicks : SignUpEvent
data object FacebookClicks : SignUpEvent
data object LoginClicks : SignUpEvent
data object PrivacyClicks : SignUpEvent
data object TermsClicks : SignUpEvent
data object DismissSnackbar : SignUpEvent