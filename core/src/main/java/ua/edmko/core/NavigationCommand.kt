package ua.edmko.core

import androidx.navigation.NavOptionsBuilder

interface NavigationCommand<T : Any> {
    val route: T
    val builder: NavOptionsBuilder.() -> Unit
}
