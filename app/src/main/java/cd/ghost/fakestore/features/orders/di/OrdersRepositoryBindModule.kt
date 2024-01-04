package cd.ghost.fakestore.features.orders.di

import cd.ghost.fakestore.features.orders.repositories.AdapterOrdersRepository
import cd.ghost.orders.domain.repositories.OrdersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface OrdersRepositoryBindModule {

    @Binds
    fun bindOrdersRepository(repo: AdapterOrdersRepository): OrdersRepository

}