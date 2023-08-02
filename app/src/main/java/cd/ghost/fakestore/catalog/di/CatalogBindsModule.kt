package cd.ghost.fakestore.catalog.di

import cd.ghost.catalog.domain.ProductsRepository
import cd.ghost.fakestore.catalog.CatalogProductsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CatalogBindsModule {

    @Binds
    @Singleton
    abstract fun bindCatalogProducts(repo: CatalogProductsRepository): ProductsRepository

}