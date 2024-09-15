package ua.edmko.navigation.commands

import androidx.navigation.NavOptionsBuilder
import ua.edmko.core.NavigationCommand
import ua.edmko.onboarding.OnboardingRoute

internal val OnboardingCommand = object : NavigationCommand<OnboardingRoute> {

    override val route: OnboardingRoute = OnboardingRoute
    override val builder: NavOptionsBuilder.() -> Unit = {}
}