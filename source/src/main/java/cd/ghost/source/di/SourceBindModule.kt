package cd.ghost.source.di

import cd.ghost.source.DefAuthSource
import cd.ghost.source.DefProductsSource
import cd.ghost.source.InMemoryCartDataSource
import cd.ghost.source.auth.AuthSource
import cd.ghost.source.carts.CartDataSource
import cd.ghost.source.products.ProductsSource
import cd.ghost.source.settings.AppSettings
import cd.ghost.source.SharedPreferencesAppSettings
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SourceBindModule {

    @Binds
    fun bindProductsSource(source: DefProductsSource): ProductsSource

    @Binds
    fun bindAatuSource(source: DefAuthSource): AuthSource

    @Binds
    @Singleton
    fun bindCartSource(source: InMemoryCartDataSource): CartDataSource

    @Binds
    @Singleton
    fun bindSettingsSource(settings: SharedPreferencesAppSettings): AppSettings

}