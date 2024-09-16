package ua.edmko.navigation

import ua.edmko.core.NavigationManager
import ua.edmko.navigation.commands.GoalSetupCommand
import ua.edmko.navigation.commands.SignUpCommand
import ua.edmko.onboarding.OnboardingNavigator
import ua.edmko.signup.SignUpNavigator
import javax.inject.Inject

internal class Navigator @Inject constructor(
    private val navigationManager: NavigationManager,
) : OnboardingNavigator, SignUpNavigator {

    override fun toSignUp() {
        navigationManager.navigate(SignUpCommand)
    }

    override fun toGoalSetup() {
        navigationManager.navigate(GoalSetupCommand)
    }
}