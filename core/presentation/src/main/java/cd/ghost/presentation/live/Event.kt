package cd.ghost.presentation.live

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Event<T>(value: T) {
    private var _value: T? = value
    fun get(): T? {
        return _value.also { _value = null }
    }
}
/**
 * Short name of live value with event.
 */
typealias LiveEventValue<T> = LiveValue<Event<T>>

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this
typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>
typealias LiveEvent<T> = LiveData<Event<T>>
typealias EventListener<T> = (T) -> Unit

fun <T> MutableLiveEvent<T>.publish(value: T) {
    this.value = Event(value)
}

fun <T> LiveEvent<T>.observeEvent(
    lifecycleOwner: LifecycleOwner, listener: EventListener<T>
) {
    this.observe(lifecycleOwner) {
        it?.get()?.let { value ->
            listener(value)
        }
    }
}