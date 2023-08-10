package cd.ghost.fakestore.features.cart.di

import cd.ghost.cart.domain.repos.CartRepository
import cd.ghost.fakestore.features.cart.repos.DefaultCartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CartsReposModule {

    @Binds
    abstract fun bindCartRepo(repo: DefaultCartRepository): CartRepository

}