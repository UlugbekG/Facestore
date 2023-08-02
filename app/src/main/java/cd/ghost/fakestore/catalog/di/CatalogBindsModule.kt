package cd.ghost.fakestore.catalog.di

import cd.ghost.catalog.domain.ProductsRepository
import cd.ghost.fakestore.catalog.CatalogProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class CatalogBindsModule {

    @Binds
    abstract fun bindCatalogProducts(repo: CatalogProductsRepository): ProductsRepository

}