package cd.ghost.common

sealed class Container<out T> {
    class Success<T>(val data: T) : Container<T>()
    object Error : Container<Nothing>()
    object Pending : Container<Nothing>()
}