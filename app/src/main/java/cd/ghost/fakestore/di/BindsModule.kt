package cd.ghost.fakestore.di

import cd.ghost.data.CartDataRepository
import cd.ghost.data.DefaultCartDataRepository
import cd.ghost.data.DefaultProductsDataRepository
import cd.ghost.data.ProductsDataRepository
import cd.ghost.data.sources.carts.CartDataSource
import cd.ghost.data.sources.carts.InMemoryCartDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    @Singleton
    abstract fun bindProductsRepo(repo: DefaultProductsDataRepository): ProductsDataRepository

    @Binds
    @Singleton
    abstract fun bindCartsRepo(repo: DefaultCartDataRepository): CartDataRepository

    @Binds
    @Singleton
    abstract fun bindCartSource(source: InMemoryCartDataSource): CartDataSource

}