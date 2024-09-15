package ua.edmko.core

import kotlinx.coroutines.flow.Flow
import ua.edmko.core.NavigationCommand

interface NavigationManager {

    fun commands(): Flow<NavigationCommand<*>>

    fun navigate(directions: NavigationCommand<*>)

    fun back()
}
