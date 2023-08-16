package cd.ghost.common

import kotlinx.coroutines.CoroutineScope

interface CoreProvider {
    val resources: Resources
    val globalScope: CoroutineScope
    val debouncePeriodMillis: Long get() = 200L
}