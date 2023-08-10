package cd.ghost.common

import kotlinx.coroutines.runBlocking

sealed class Container<out T> {

    fun <R> map(mapper: ((T) -> R)? = null): Container<R> {
        return runBlocking {
            val suspendMapper: (suspend (T) -> R)? =
                if (mapper == null) {
                    null
                } else {
                    {
                        mapper(it)
                    }
                }
            suspendMap(suspendMapper)
        }
    }

    abstract suspend fun <R> suspendMap(mapper: (suspend (T) -> R)? = null): Container<R>

    abstract fun getOrNull(): T?

    class Success<T>(val value: T) : Container<T>() {
        override suspend fun <R> suspendMap(mapper: (suspend (T) -> R)?): Container<R> {
            if (mapper == null) throw IllegalStateException("Can't map Container.Success without mapper")
            return try {
                Success(mapper(value))
            } catch (e: Exception) {
                Error(e)
            }
        }

        override fun getOrNull(): T? {
            return value
        }
    }

    class Error(val error: Exception) : Container<Nothing>() {
        override suspend fun <R> suspendMap(mapper: (suspend (Nothing) -> R)?): Container<R> {
            return this
        }

        override fun getOrNull(): Nothing? {
            return null
        }
    }

    object Pending : Container<Nothing>() {
        override suspend fun <R> suspendMap(mapper: (suspend (Nothing) -> R)?): Container<R> {
            return this
        }

        override fun getOrNull(): Nothing? {
            return null
        }
    }
}