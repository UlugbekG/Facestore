package cd.ghost.fakestore.di

import android.content.Context
import cd.ghost.common.Core
import cd.ghost.common.CoreProvider
import cd.ghost.common.Resources
import cd.ghost.common.flow.DefaultLazyFlowSubjectFactory
import cd.ghost.common.flow.LazyFlowSubjectFactory
import cd.ghost.common_impl.DefaultCoreProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreProviderModule {

    @Provides
    fun provideCoreProvider(
        @ApplicationContext context: Context
    ): CoreProvider {
        return DefaultCoreProvider(
            context = context
        )
    }

    @Provides
    fun provideGlobalScope(): CoroutineScope {
        return Core.globalScope
    }

    @Provides
    fun provideResources(): Resources {
        return Core.resources
    }

    @Singleton
    @Provides
    fun provideLazyFlowSubjectFactory(): LazyFlowSubjectFactory {
        return DefaultLazyFlowSubjectFactory(
            dispatcher = Dispatchers.IO,
        )
    }

}