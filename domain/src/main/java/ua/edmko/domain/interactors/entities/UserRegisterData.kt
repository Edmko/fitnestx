package ua.edmko.domain.interactors.entities

data class UserRegisterData(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)