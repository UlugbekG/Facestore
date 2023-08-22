package cd.ghost.fakestore.main.di

import cd.ghost.fakestore.main.NavComponentRouter
import cd.ghost.fakestore.main.NavControllerHolder
import cd.ghost.source.settings.AppSettings
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
    fun provideNavigationComponent(
        appSettings: AppSettings
    ): NavComponentRouter {
        return NavComponentRouter(appSettings)
    }

    @Provides
    @Singleton
    fun provideNavigationHolder(
        navComponent: NavComponentRouter
    ): NavControllerHolder {
        return navComponent
    }

}