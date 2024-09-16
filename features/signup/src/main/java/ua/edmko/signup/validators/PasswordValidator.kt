package ua.edmko.signup.validators

import ua.edmko.core_ui.models.validator.Invalid
import ua.edmko.core_ui.models.TextWrapper
import ua.edmko.core_ui.models.validator.Valid
import ua.edmko.core_ui.models.validator.Validation
import ua.edmko.core_ui.models.validator.Validator
import ua.edmko.signup.R
import ua.edmko.signup.configs.PASSWORD_MAX_LENGTH
import ua.edmko.signup.configs.PASSWORD_MIN_LENGTH

class PasswordValidator : Validator<String> {

    override fun validate(value: String): Validation {
        return when {
            value.length < PASSWORD_MIN_LENGTH -> Invalid(TextWrapper.from(R.string.min_length_error_title, PASSWORD_MIN_LENGTH))
            value.length > PASSWORD_MAX_LENGTH -> Invalid(TextWrapper.from(R.string.max_length_error_title, PASSWORD_MAX_LENGTH))
            else -> Valid
        }
    }
}