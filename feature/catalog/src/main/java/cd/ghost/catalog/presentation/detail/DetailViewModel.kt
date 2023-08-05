package cd.ghost.catalog.presentation.detail

import androidx.lifecycle.ViewModel
import cd.ghost.common.MutableLiveEvent
import cd.ghost.common.asLiveData
import cd.ghost.common.publish
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
) : ViewModel() {

    private val _message = MutableLiveEvent<Int>()
    val message = _message.asLiveData()

    fun initProductId(productId: Int?) {
        val id = productId ?: return
        _message.publish(id)
    }

}