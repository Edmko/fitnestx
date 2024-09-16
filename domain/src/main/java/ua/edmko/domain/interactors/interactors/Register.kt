package ua.edmko.domain.interactors.interactors

import kotlinx.coroutines.withContext
import ua.edmko.domain.interactors.entities.CoroutineDispatchers
import ua.edmko.domain.interactors.entities.UserRegisterData
import ua.edmko.domain.interactors.repositories.UserRepository
import javax.inject.Inject

class Register @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatchers: CoroutineDispatchers,
) : Interactor<Register.Params>() {

    data class Params(val data: UserRegisterData)

    override suspend fun doWork(params: Params) = withContext(dispatchers.io) {
        userRepository.register(params.data)
    }
}