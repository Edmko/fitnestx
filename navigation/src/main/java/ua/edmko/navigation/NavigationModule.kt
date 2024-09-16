package ua.edmko.navigation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.edmko.core.NavigationManager
import ua.edmko.onboarding.OnboardingNavigator
import ua.edmko.signup.SignUpNavigator
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {

    @Singleton
    @Binds
    fun bindNavigationManager(impl: NavigationManagerImpl): NavigationManager

    @Singleton
    @Binds
    fun bindOnboardingNavigator(navigator: Navigator): OnboardingNavigator

    @Singleton
    @Binds
    fun bindSignUpNavigator(navigator: Navigator): SignUpNavigator
}