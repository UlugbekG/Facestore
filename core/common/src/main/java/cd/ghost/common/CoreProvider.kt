package cd.ghost.common

import kotlinx.coroutines.CoroutineScope

interface CoreProvider {
    val resources: Resources
    val globalScope: CoroutineScope
    val debouncePeriodMillis: Long get() = 200L
    val localTimeoutMillis: Long get() = 3_000L
}