package cd.ghost.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import cd.ghost.common.AuthException
import cd.ghost.common.CommonUi
import cd.ghost.common.Container
import cd.ghost.common.Core
import cd.ghost.presentation.R
import cd.ghost.presentation.databinding.CorePresentationResultViewBinding
import cd.ghost.presentation.live.LiveValue
import cd.ghost.presentation.observeStateOn
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResultView @JvmOverloads constructor(
    context: Context,
    attrsSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrsSet, defStyleAttr, defStyleRes) {

    var container: Container<*> = Container.Pending
        set(value) {
            field = value
            notifyUpdates()
        }

    private var tryAgainListener: (() -> Unit)? = null

    private var binding: CorePresentationResultViewBinding

    init {
        binding = CorePresentationResultViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            false
        )
        addView(binding.root)

        if (isInEditMode) {
            container = Container.Success("")
        } else {
            binding.resultProgressBar.isVisible = false
            binding.resultErrorContainer.isVisible = false
            binding.internalResultContainer.isVisible = false
            children.forEach {
                it.isVisible = false
            }
            container = Container.Pending
        }

        binding.tryAgainButton.setOnClickListener {
            if (isAuthError()) {
                Core.appRestarter.restartApp()
            } else {
                tryAgainListener?.invoke()
            }
            tryAgainListener?.invoke()
        }

    }

    /**
     * Assign try-again listener which is called when [container] has error
     * value ([Container.Error]) and user presses Try Again button. Usually
     * you need to try ro reload data in the [onTryAgain] callback.
     */
    fun setTryAgainListener(onTryAgain: () -> Unit) {
        this.tryAgainListener = onTryAgain
    }

    private fun notifyUpdates() {
        val container = this.container
        binding.resultProgressBar.isVisible = container is Container.Pending
        binding.resultErrorContainer.isVisible = container is Container.Error
        binding.internalResultContainer.isVisible = container !is Container.Success

        if (container is Container.Error) {
            val exception = container.error
            binding.resultErrorTextView.text = exception.message
            binding.tryAgainButton.setText(
                if (isAuthError()) {
                    R.string.core_presentation_logout
                } else {
                    R.string.core_presentation_try_again
                }
            )

            children.forEach {
                if (it != binding.root) {
                    it.isVisible = container is Container.Success
                }
            }
        }
    }

    private fun isAuthError() = container.let {
        it is Container.Error && it.error is AuthException
    }

}

/**
 * Observe the specified [liveData] and call [onSuccess] callback
 * if the [liveData] contains [Container.Success] value.
 */
fun <T> ResultView.observe(
    owner: LifecycleOwner,
    liveData: LiveData<Container<T>>,
    onSuccess: (T) -> Unit
) {
    liveData.observe(owner) {
        container = it
        if (it is Container.Success) {
            onSuccess(it.value)
        }
    }
}

/**
 * Observe the specified [liveValue] and call [onSuccess] callback
 * if the [liveValue] contains [Container.Success] value.
 */
fun <T> ResultView.observe(
    owner: LifecycleOwner,
    liveValue: LiveValue<Container<T>>,
    onSuccess: (T) -> Unit
) {
    liveValue.observe(owner) {
        container = it
        if (it is Container.Success) {
            onSuccess(it.value)
        }
    }
}

/**
 * Observe the specified [flow] and call [onSuccess] callback
 * if the [flow] contains [Container.Success] value.
 */
fun <T> ResultView.observe(
    owner: LifecycleOwner,
    flow: Flow<Container<T>>,
    onSuccess: (T) -> Unit
) {
    flow.observeStateOn(owner) {
        container = it
        if (it is Container.Success) {
            onSuccess(it.value)
        }
    }
}