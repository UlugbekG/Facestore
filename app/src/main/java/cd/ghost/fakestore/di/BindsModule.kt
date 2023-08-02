package cd.ghost.fakestore.di

import cd.ghost.data.DataProductsRepository
import cd.ghost.data.DataProductsRepositoryImpl
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
    abstract fun bindProductsApi(repo: DataProductsRepositoryImpl): DataProductsRepository
}