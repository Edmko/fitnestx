package ua.edmko.domain.interactors.interactors

import kotlinx.coroutines.flow.Flow
import ua.edmko.domain.interactors.interactors.entities.UserState
import ua.edmko.domain.interactors.repositories.UserRepository
import javax.inject.Inject

class ObserveUserState @Inject constructor(
    private val userRepository: UserRepository
) : SubjectInteractor<Unit, UserState>() {

    override fun createObservable(params: Unit): Flow<UserState> {
        return userRepository.observeUserState()
    }
}