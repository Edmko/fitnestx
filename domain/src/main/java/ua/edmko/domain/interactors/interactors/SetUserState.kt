package ua.edmko.domain.interactors.interactors

import kotlinx.coroutines.withContext
import ua.edmko.domain.interactors.entities.CoroutineDispatchers
import ua.edmko.domain.interactors.interactors.entities.UserState
import ua.edmko.domain.interactors.repositories.UserRepository
import javax.inject.Inject

class SetUserState @Inject constructor(
    private val repository: UserRepository,
    private val dispatchers: CoroutineDispatchers,
) : Interactor<SetUserState.Params>() {

    data class Params(val userState: UserState)

    override suspend fun doWork(params: Params) = withContext(dispatchers.io) {
        repository.setUserState(params.userState)
    }
}