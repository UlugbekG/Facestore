package cd.ghost.fakestore.main.di

import cd.ghost.fakestore.main.NavComponentRouter
import cd.ghost.fakestore.main.NavControllerHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Provides
    @Singleton
    fun provideNavigationComponent(): NavComponentRouter {
        return NavComponentRouter()
    }

    @Provides
    @Singleton
    fun provideNavigationHolder(
        navComponent: NavComponentRouter
    ): NavControllerHolder {
        return navComponent
    }

}