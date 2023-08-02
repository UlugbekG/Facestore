package cd.ghost.common

sealed class Container<out T> {
    class Success<T>(val data: T) : Container<T>()
    class Error(val error: Exception) : Container<Nothing>()
    object Pending : Container<Nothing>()
}