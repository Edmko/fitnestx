package ua.edmko.navigation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch
import ua.edmko.core.NavigationManager
import ua.edmko.goalsetup.GoalSetupRoute
import ua.edmko.navigation.commands.BackCommand
import ua.edmko.navigation.commands.OnboardingCommand
import ua.edmko.navigation.commands.SignUpCommand
import ua.edmko.onboarding.OnboardingRoute
import ua.edmko.onboarding.OnboardingStartScreen
import ua.edmko.signup.SignUpRoute
import ua.edmko.signup.SignUpScreen

@Composable
fun NavigationComponent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigationManager: NavigationManager,
) {
    val coroutine = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutine.launch {
            navigationManager.commands().collect { command ->
                Log.d("NavigationComponent", "command = $command")
                if (command == BackCommand) {
                    navController.navigateUp()
                } else {
                    navController.navigate(command.route, command.builder)
                }
            }
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = OnboardingCommand.route,
    ) {
        composable<OnboardingRoute> { OnboardingStartScreen() }
        composable<SignUpRoute> { SignUpScreen() }
        composable<GoalSetupRoute> {
            Box(Modifier.fillMaxSize()) {
                Text(text = "Goal setup screen", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}