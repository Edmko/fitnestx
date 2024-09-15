package ua.edmko.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import ua.edmko.core.NavigationCommand
import ua.edmko.core.NavigationManager
import ua.edmko.navigation.commands.BackCommand
import javax.inject.Inject

internal class NavigationManagerImpl @Inject constructor() : NavigationManager {

    private val _commands: MutableSharedFlow<NavigationCommand<*>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun commands(): Flow<NavigationCommand<*>> = _commands

    override fun navigate(directions: NavigationCommand<*>) {
        _commands.tryEmit(directions)
    }

    override fun back() {
        _commands.tryEmit(BackCommand)
    }
}