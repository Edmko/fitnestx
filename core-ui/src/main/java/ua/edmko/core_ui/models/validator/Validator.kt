package ua.edmko.core_ui.models.validator

interface Validator<T> {

    fun validate(value: T): Validation
}

fun <T> Validator<T>.isValid(value: T) = this.validate(value) is Valid