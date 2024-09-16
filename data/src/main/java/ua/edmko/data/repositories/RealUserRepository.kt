package ua.edmko.data.repositories

import kotlinx.coroutines.flow.Flow
import ua.edmko.domain.interactors.entities.UserRegisterData
import ua.edmko.domain.interactors.interactors.entities.UserState
import ua.edmko.domain.interactors.repositories.UserRepository
import javax.inject.Inject

class RealUserRepository @Inject constructor() : UserRepository {

    override suspend fun register(data: UserRegisterData) {
        TODO("Not yet implemented")
    }

    override suspend fun setUserState(userState: UserState) {
        TODO("Not yet implemented")
    }

    override fun observeUserState(): Flow<UserState> {
        TODO("Not yet implemented")
    }
}