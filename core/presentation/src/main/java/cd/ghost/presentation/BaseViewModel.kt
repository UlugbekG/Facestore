package cd.ghost.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cd.ghost.common.Core
import cd.ghost.common.Resources
import cd.ghost.presentation.live.Event
import cd.ghost.presentation.live.LiveEventValue
import cd.ghost.presentation.live.LiveValue
import cd.ghost.presentation.live.MutableLiveValue
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
open class BaseViewModel : ViewModel() {

    /**
     * View model scope with pre-assigned error handler.
     * Error handler is taken from [Core.errorHandler].
     */
    protected val viewModelScope: CoroutineScope by lazy {
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            Log.d("ViewModelErrorHandler", exception.message.toString())
        }
        CoroutineScope(SupervisorJob() + Dispatchers.Main + errorHandler)
    }
    protected val resources: Resources get() = Core.resources

    private val debounceFlow = MutableSharedFlow<() -> Unit>(
        replay = 1,
        extraBufferCapacity = 42
    )

    init {
        viewModelScope.launch {
            debounceFlow.sample(Core.debouncePeriodMillis).collect {
                it()
            }
        }
    }

    /**
     * Create a [LiveValue]. You can change the value inside [LiveValue]
     * within your view-model class but you can't do this in other classes.
     */
    protected fun <T> liveValue(): LiveValue<T> {
        return MutableLiveValue()
    }

    /**
     * Create a [LiveValue] with the predefined value.
     * You can change the value inside [LiveValue] within your view-model
     * class but you can't do this in other classes.
     */
    protected fun <T> liveValue(value: T): LiveValue<T> {
        return MutableLiveValue(MutableLiveData(value))
    }

    /**
     * Assign a new value to the [LiveValue] container.
     */
    protected var <T> LiveValue<T>.value: T
        get() = this.requireValue()
        set(value) {
            (this as? MutableLiveValue)?.setValue(value)
        }

    /**
     * Create an instance of [LiveValue] which holds [Event].
     * Used to fire one-time side-effect events.
     */
    protected fun <T> liveEvent(): LiveEventValue<T> {
        return MutableLiveValue()
    }

    /**
     * Publish a new event to the [LiveEventValue] created by [liveEvent].
     */
    protected fun <T> LiveEventValue<T>.publish(event: T) {
        this.value = Event(event)
    }

    /**
     * Call an action only once within the [Core.debouncePeriodMillis] period.
     */
    protected fun debounce(block: () -> Unit) {
        debounceFlow.tryEmit(block)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}