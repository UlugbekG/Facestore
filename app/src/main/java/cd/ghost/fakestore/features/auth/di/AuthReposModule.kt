package cd.ghost.fakestore.features.auth.di

import cd.ghost.auth.domain.repositories.AuthRepository
import cd.ghost.fakestore.features.auth.repositories.AdapterAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthReposModule {

    @Binds
    @Singleton
    fun bindAuthRepo(repo: AdapterAuthRepository): AuthRepository

}