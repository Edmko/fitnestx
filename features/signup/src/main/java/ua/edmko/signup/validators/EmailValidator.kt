package ua.edmko.signup.validators

import android.util.Patterns
import ua.edmko.core_ui.models.validator.Invalid
import ua.edmko.core_ui.models.TextWrapper
import ua.edmko.core_ui.models.validator.Valid
import ua.edmko.core_ui.models.validator.Validation
import ua.edmko.core_ui.models.validator.Validator
import ua.edmko.signup.R

class EmailValidator : Validator<String> {

    override fun validate(value: String): Validation {
        return when {
            Patterns.EMAIL_ADDRESS.matcher(value).matches() -> Valid
            else -> Invalid(TextWrapper.from(R.string.email_validation_error))
        }
    }
}