package ua.edmko.signup.validators

import ua.edmko.core_ui.models.validator.Invalid
import ua.edmko.core_ui.models.TextWrapper
import ua.edmko.core_ui.models.validator.Valid
import ua.edmko.core_ui.models.validator.Validation
import ua.edmko.core_ui.models.validator.Validator
import ua.edmko.signup.R
import ua.edmko.signup.configs.NAME_MAX_LENGTH
import ua.edmko.signup.configs.NAME_MIN_LENGTH

class NameValidator : Validator<String> {

    override fun validate(value: String): Validation {
        return when {
            value.length < NAME_MIN_LENGTH -> Invalid(TextWrapper.from(R.string.min_length_error_title, NAME_MIN_LENGTH))
            value.length > NAME_MAX_LENGTH -> Invalid(TextWrapper.from(R.string.max_length_error_title, NAME_MAX_LENGTH))
            value.all { it.isLetter() }.not() -> Invalid(TextWrapper.from(R.string.name_unaccepted_symbols))
            else -> Valid
        }
    }
}