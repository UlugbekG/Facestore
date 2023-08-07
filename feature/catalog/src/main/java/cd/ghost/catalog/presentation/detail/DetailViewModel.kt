package cd.ghost.catalog.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.ghost.catalog.R
import cd.ghost.catalog.domain.GetProductByIdUseCase
import cd.ghost.catalog.domain.entity.EntityProduct
import cd.ghost.common.Container
import cd.ghost.common.MutableLiveEvent
import cd.ghost.common.asLiveData
import cd.ghost.common.publish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
) : ViewModel() {

    private val _product = MutableStateFlow<Container<EntityProduct>>(
        value = Container.Pending
    )
    val product = _product.asStateFlow()

    private val _message = MutableLiveEvent<Int>()
    val message = _message.asLiveData()

    fun getProduct(productId: Int?) {
        if (productId == null) {
            _message.publish(R.string.id_not_found)
            return
        }
        viewModelScope.launch {
            getProductByIdUseCase(productId).collectLatest {
                _product.value = it
            }
        }
    }
}