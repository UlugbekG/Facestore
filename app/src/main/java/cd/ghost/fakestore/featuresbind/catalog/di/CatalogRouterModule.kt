package cd.ghost.fakestore.featuresbind.catalog.di

import cd.ghost.catalog.presentation.productlist.CatalogRouter
import cd.ghost.fakestore.featuresbind.catalog.AdapterCatalogRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class CatalogRouterModule {

    @Binds
    abstract fun bindRouter(router: AdapterCatalogRouter): CatalogRouter
}