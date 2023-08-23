package cd.ghost.data.di

import cd.ghost.data.repositories.AuthDataRepository
import cd.ghost.data.repositories.CartDataRepository
import cd.ghost.data.repositories.DefAuthDataRepository
import cd.ghost.data.repositories.DefCartDataRepository
import cd.ghost.data.repositories.DefFavoritesDataRepository
import cd.ghost.data.repositories.DefProductsDataRepository
import cd.ghost.data.repositories.FavoritesDataRepository
import cd.ghost.data.repositories.ProductsDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesBindModule {

    @Binds
    @Singleton
    fun bindProductsRepo(repo: DefProductsDataRepository): ProductsDataRepository

    @Binds
    @Singleton
    fun bindCartsRepo(repo: DefCartDataRepository): CartDataRepository

    @Binds
    @Singleton
    fun bindAuthRepo(repo: DefAuthDataRepository): AuthDataRepository

    @Binds
    @Singleton
    fun bindFavoritesRepo(repo: DefFavoritesDataRepository): FavoritesDataRepository

}