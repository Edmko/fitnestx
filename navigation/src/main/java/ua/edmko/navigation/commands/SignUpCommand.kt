package ua.edmko.navigation.commands

import androidx.navigation.NavOptionsBuilder
import ua.edmko.core.NavigationCommand
import ua.edmko.signup.SignUpRoute

val SignUpCommand = object : NavigationCommand<SignUpRoute> {

    override val route: SignUpRoute = SignUpRoute
    override val builder: NavOptionsBuilder.() -> Unit = {}
}