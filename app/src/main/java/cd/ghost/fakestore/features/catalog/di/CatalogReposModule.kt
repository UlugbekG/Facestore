package cd.ghost.fakestore.features.catalog.di

import cd.ghost.catalog.domain.repos.CartRepository
import cd.ghost.catalog.domain.repos.FilterRepository
import cd.ghost.catalog.domain.repos.ProductsRepository
import cd.ghost.fakestore.features.catalog.repos.AdapterCartRepository
import cd.ghost.fakestore.features.catalog.repos.AdapterFilterRepository
import cd.ghost.fakestore.features.catalog.repos.AdapterProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CatalogReposModule {

    @Binds
    abstract fun bindCatalogProducts(repo: AdapterProductsRepository): ProductsRepository

    @Binds
    abstract fun bindFilter(repo: AdapterFilterRepository): FilterRepository

    @Binds
    abstract fun bindCart(repo: AdapterCartRepository): CartRepository

}