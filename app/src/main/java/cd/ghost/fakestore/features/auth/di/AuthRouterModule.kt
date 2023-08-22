package cd.ghost.fakestore.features.auth.di

import cd.ghost.auth.AuthRouter
import cd.ghost.fakestore.features.auth.AdapterAuthRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthRouterModule {

    @Binds
    fun bindAuthRouter(router: AdapterAuthRouter): AuthRouter

}