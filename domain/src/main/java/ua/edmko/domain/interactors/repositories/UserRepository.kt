package ua.edmko.domain.interactors.repositories

import kotlinx.coroutines.flow.Flow
import ua.edmko.domain.interactors.entities.UserRegisterData
import ua.edmko.domain.interactors.interactors.entities.UserState

interface UserRepository {

    suspend fun register(data: UserRegisterData)

    suspend fun setUserState(userState: UserState)

    fun observeUserState(): Flow<UserState>
}