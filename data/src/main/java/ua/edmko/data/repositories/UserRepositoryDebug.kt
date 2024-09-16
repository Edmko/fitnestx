package ua.edmko.data.repositories

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import ua.edmko.domain.interactors.entities.UserRegisterData
import ua.edmko.domain.interactors.interactors.entities.UserState
import ua.edmko.domain.interactors.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryDebug @Inject constructor() : UserRepository {

    private val userState = MutableStateFlow(UserState.NEW)
    private var userData: UserRegisterData? = null

    override suspend fun register(data: UserRegisterData) {
        delay(3000L)
        userData = data
    }

    override suspend fun setUserState(userState: UserState) {
        this.userState.value = userState
    }

    override fun observeUserState(): Flow<UserState> {
        return userState.asStateFlow().onEach { delay(3000L) }
    }
}