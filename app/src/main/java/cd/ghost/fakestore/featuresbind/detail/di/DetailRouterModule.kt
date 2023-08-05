package cd.ghost.fakestore.featuresbind.detail.di

import cd.ghost.catalog.presentation.detail.DetailRouter
import cd.ghost.fakestore.featuresbind.detail.AdapterDetailRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailRouterModule {

    @Binds
    abstract fun bindRouter(router: AdapterDetailRouter): DetailRouter

}