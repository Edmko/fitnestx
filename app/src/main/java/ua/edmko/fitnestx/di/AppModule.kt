package ua.edmko.fitnestx.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import ua.edmko.domain.interactors.entities.CoroutineDispatchers
import ua.edmko.domain.interactors.entities.Environment
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class AppModule {

    @Singleton
    @Provides
    fun provideEnvironment(): Environment = Environment.FAKE

    @Singleton
    @Provides
    fun provideDispatchers(): CoroutineDispatchers = CoroutineDispatchers(
        io = Dispatchers.IO,
        main = Dispatchers.Main,
        computation = Dispatchers.Default,
    )
}