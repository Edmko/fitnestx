package ua.edmko.onboarding

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.edmko.core.BaseViewModel
import ua.edmko.core_ui.controllers.SnackbarMessage
import ua.edmko.core_ui.controllers.UserMessage
import ua.edmko.domain.interactors.interactors.InvokeError
import ua.edmko.domain.interactors.interactors.InvokeStarted
import ua.edmko.domain.interactors.interactors.InvokeSuccess
import ua.edmko.domain.interactors.interactors.SetUserState
import ua.edmko.domain.interactors.interactors.entities.UserState
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val setUserState: SetUserState,
    private val navigator: OnboardingNavigator,
) : BaseViewModel<OnboardingViewState, OnboardingEvent>(OnboardingViewState()) {

    override fun obtainEvent(viewEvent: OnboardingEvent) {
        when (viewEvent) {
            Complete -> completeOnboarding()
            DismissSnackbar -> viewState = viewState.copy(snackbarMessage = null)
        }
    }

    private fun completeOnboarding() = viewModelScope.launch {
        setUserState(SetUserState.Params(UserState.SIGN_UP)).collect {
            when (it) {
                is InvokeError -> {
                    viewState = viewState.copy(
                        snackbarMessage = SnackbarMessage.from(UserMessage.from("Something went wrong"))
                    )
                }
                InvokeStarted -> Unit
                InvokeSuccess -> navigator.toSignUp()
            }
        }
    }
}