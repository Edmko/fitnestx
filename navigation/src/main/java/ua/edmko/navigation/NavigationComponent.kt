package ua.edmko.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch
import ua.edmko.core.NavigationManager
import ua.edmko.navigation.commands.BackCommand
import ua.edmko.navigation.commands.OnboardingCommand
import ua.edmko.navigation.commands.SignUpCommand
import ua.edmko.onboarding.OnboardingRoute
import ua.edmko.onboarding.OnboardingStartScreen
import ua.edmko.signup.SignUpRoute
import ua.edmko.signup.SignUpScreen

@Composable
fun NavigationComponent(
    navController: NavHostController,
    navigationManager: NavigationManager,
) {
    val coroutine = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutine.launch {
            navigationManager.commands().collect { command ->
                if (command == BackCommand) {
                    navController.navigateUp()
                } else {
                    navController.navigate(command.route, command.builder)
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = OnboardingCommand.route,
    ) {
        composable<OnboardingRoute> {
            OnboardingStartScreen {
                navController.navigate(SignUpCommand.route)
            }
        }
        composable<SignUpRoute> {
            SignUpScreen()
        }
    }
}