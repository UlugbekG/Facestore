package cd.ghost.common

import kotlinx.coroutines.CoroutineScope

object Core {
    private lateinit var coreProvider: CoreProvider
    val resources: Resources get() = coreProvider.resources
    val appRestarter:AppRestarter get() = coreProvider.appRestarter
    val globalScope: CoroutineScope get() = coreProvider.globalScope
    val debouncePeriodMillis get() = coreProvider.debouncePeriodMillis
    val localTimeoutMillis get() = coreProvider.localTimeoutMillis
    fun init(coreProvider: CoreProvider) {
        this.coreProvider = coreProvider
    }
}