package cd.ghost.fakestore.di

import cd.ghost.data.DataCartsRepository
import cd.ghost.data.DataProductsRepository
import cd.ghost.data.DefaultDataCartsRepository
import cd.ghost.data.DefaultDataProductsRepository
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
    abstract fun bindProductsRepo(repo: DefaultDataProductsRepository): DataProductsRepository

    @Binds
    @Singleton
    abstract fun bindCartsRepo(repo: DefaultDataCartsRepository): DataCartsRepository

}