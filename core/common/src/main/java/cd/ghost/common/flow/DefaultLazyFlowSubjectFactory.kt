package cd.ghost.common.flow

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

class DefaultLazyFlowSubjectFactory(
    private val dispatcher: CoroutineDispatcher,
    private val globalScope: CoroutineScope,
    private val cacheTimeoutMillis: Long = 1000
) : LazyFlowSubjectFactory {

    override fun <T> create(loader: ValueLoader<T>): LazyFlowSubject<T> {
        return DefaultLazyFlowSubject(loader, dispatcher, globalScope, cacheTimeoutMillis)
    }

}