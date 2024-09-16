package ua.edmko.navigation.commands

import androidx.navigation.NavOptionsBuilder
import ua.edmko.core.NavigationCommand
import ua.edmko.goalsetup.GoalSetupRoute

val GoalSetupCommand = object : NavigationCommand<GoalSetupRoute> {

    override val route: GoalSetupRoute = GoalSetupRoute
    override val builder: NavOptionsBuilder.() -> Unit = {}
}