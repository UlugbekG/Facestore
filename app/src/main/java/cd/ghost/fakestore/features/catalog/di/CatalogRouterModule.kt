package cd.ghost.fakestore.features.catalog.di

import cd.ghost.catalog.presentation.productlist.ProductsDestinationId
import cd.ghost.fakestore.features.catalog.AdapterProductsDestinationId
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class CatalogRouterModule {

    @Binds
    abstract fun bindCatalogRouter(router: AdapterProductsDestinationId): ProductsDestinationId

}