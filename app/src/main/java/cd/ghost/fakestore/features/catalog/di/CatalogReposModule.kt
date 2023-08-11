package cd.ghost.fakestore.features.catalog.di

import cd.ghost.catalog.domain.repos.CartRepository
import cd.ghost.catalog.domain.repos.FilterRepository
import cd.ghost.catalog.domain.repos.ProductsRepository
import cd.ghost.fakestore.features.catalog.repos.DefaultCartRepository
import cd.ghost.fakestore.features.catalog.repos.DefaultCatalogsRepository
import cd.ghost.fakestore.features.catalog.repos.DefaultFilterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CatalogReposModule {

    @Binds
    abstract fun bindCatalogProducts(repo: DefaultCatalogsRepository): ProductsRepository

    @Binds
    abstract fun bindFilter(repo: DefaultFilterRepository): FilterRepository

    @Binds
    abstract fun bindCart(repo: DefaultCartRepository): CartRepository

}