package cd.ghost.fakestore.features.cart.di

import cd.ghost.cart.presentation.CartRouter
import cd.ghost.fakestore.features.cart.AdapterCartRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CartRouterModule {

    @Binds
    abstract fun bindCartRouter(router: AdapterCartRouter): CartRouter
}