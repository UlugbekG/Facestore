package cd.ghost.fakestore.featuresbind.catalog.di

import cd.ghost.catalog.domain.repos.ProductsRepository
import cd.ghost.fakestore.featuresbind.catalog.repos.CatalogsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CatalogReposModule {

    @Binds
    abstract fun bindCatalogProducts(repo: CatalogsRepository): ProductsRepository

}