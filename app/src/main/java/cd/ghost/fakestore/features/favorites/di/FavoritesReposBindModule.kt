package cd.ghost.fakestore.features.favorites.di

import cd.ghost.catalog.domain.repositories.FavoritesRepository
import cd.ghost.fakestore.features.favorites.DefFavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FavoritesReposBindModule {

    @Binds
    fun bindFavoritesRepo(repo: DefFavoritesRepository): FavoritesRepository

}