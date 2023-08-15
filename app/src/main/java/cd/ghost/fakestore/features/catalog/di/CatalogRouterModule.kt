package cd.ghost.fakestore.features.catalog.di

import cd.ghost.catalog.CatalogRouter
import cd.ghost.fakestore.features.catalog.AdapterCatalogRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class CatalogRouterModule {

    @Binds
    abstract fun bindCatalogRouter(router: AdapterCatalogRouter): CatalogRouter

}