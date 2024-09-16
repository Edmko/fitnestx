package ua.edmko.onboarding

import ua.edmko.core.Event

sealed interface OnboardingEvent : Event
data object Complete : OnboardingEvent
data object DismissSnackbar : OnboardingEvent