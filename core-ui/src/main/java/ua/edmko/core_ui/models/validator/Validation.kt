package ua.edmko.core_ui.models.validator

import ua.edmko.core_ui.models.TextWrapper

sealed interface Validation

data object Valid : Validation

data class Invalid(val message: TextWrapper) : Validation

fun Validation.isValid() = this == Valid

val Validation.messageOrNull: TextWrapper?
    get() = if (this is Invalid) message else null