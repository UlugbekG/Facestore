package cd.ghost.fakestore.di

import android.content.Context
import cd.ghost.common.ActivityRequired
import cd.ghost.common.AppRestarter
import cd.ghost.common.CommonUi
import cd.ghost.common.Core
import cd.ghost.common.CoreProvider
import cd.ghost.common.Resources
import cd.ghost.common.flow.DefaultLazyFlowSubjectFactory
import cd.ghost.common.flow.LazyFlowSubjectFactory
import cd.ghost.common_impl.DefaultCoreProvider
import cd.ghost.presentation.AndroidCommonUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ElementsIntoSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreProviderModule {

    @Provides
    fun provideCoreProvider(
        @ApplicationContext context: Context,
        appRestarter: AppRestarter
    ): CoreProvider {
        return DefaultCoreProvider(
            context = context,
            appRestarter = appRestarter
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

    @Provides
    @Singleton
    fun provideCommonUi(
        @ApplicationContext context: Context
    ): CommonUi {
        return AndroidCommonUi(context)
    }

    @Provides
    @ElementsIntoSet
    fun provideActivityRequired(
        commonUi: CommonUi
    ): Set<@JvmSuppressWildcards ActivityRequired> {
        val set = hashSetOf<ActivityRequired>()
        if (commonUi is ActivityRequired) set.add(commonUi)
        return set
    }

    @Singleton
    @Provides
    fun provideLazyFlowSubjectFactory(): LazyFlowSubjectFactory {
        return DefaultLazyFlowSubjectFactory(
            dispatcher = Dispatchers.IO,
        )
    }
}