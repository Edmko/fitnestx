package ua.edmko.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.edmko.data.repositories.RealUserRepository
import ua.edmko.data.repositories.UserRepositoryDebug
import ua.edmko.domain.interactors.entities.Environment
import ua.edmko.domain.interactors.repositories.UserRepository
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        environment: Environment,
        realProvider: Provider<RealUserRepository>,
    ): UserRepository {
        return if (environment == Environment.FAKE) UserRepositoryDebug() else realProvider.get()
    }
}