package ua.edmko.navigation.commands

import androidx.navigation.NavOptionsBuilder
import ua.edmko.core.NavigationCommand

val BackCommand = object : NavigationCommand<String> {
    override val route: String = "back"
    override val builder: NavOptionsBuilder.() -> Unit = {}
}