package cd.ghost.catalog.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.ghost.catalog.R
import cd.ghost.catalog.domain.AddToCartUseCase
import cd.ghost.catalog.domain.GetProductByIdUseCase
import cd.ghost.catalog.domain.entity.ProductWithCartInfo
import cd.ghost.common.Container
import cd.ghost.presentation.live.MutableLiveEvent
import cd.ghost.presentation.live.asLiveData
import cd.ghost.presentation.live.publish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addToCartUseCase: AddToCartUseCase,
) : ViewModel() {

    private val addToCartInProgressFlow = MutableStateFlow(false)
    private val productFlow = MutableStateFlow<Container<ProductWithCartInfo>>(Container.Pending)
    val state = combine(
        productFlow,
        addToCartInProgressFlow,
        ::merge
    )

    private val _message = MutableLiveEvent<Int>()
    val message = _message.asLiveData()

    fun getProduct(productId: Int?) {
        if (productId == null) {
            _message.publish(R.string.id_not_found)
            return
        }
        viewModelScope.launch {
            getProductByIdUseCase(productId).collectLatest {
                productFlow.value = it
            }
        }
    }

    fun addToCart() = viewModelScope.launch {
        val state = state.first().getOrNull() ?: return@launch
        try {
            addToCartInProgressFlow.value = true
            addToCartUseCase(state.product)
        } finally {
            addToCartInProgressFlow.value = false
        }
    }

    private fun merge(
        productWithCartInfo: Container<ProductWithCartInfo>,
        isAddToCartInProgress: Boolean
    ): Container<State> {
        return productWithCartInfo.map { productWithCartInfo ->
            State(
                productWithCartInfo = productWithCartInfo,
                addToCartInProgress = isAddToCartInProgress
            )
        }
    }

    data class State(
        private val productWithCartInfo: ProductWithCartInfo,
        private val addToCartInProgress: Boolean,
    ) {
        val product = productWithCartInfo.product
        val showAddToCartProgress: Boolean get() = addToCartInProgress
        val showAddToCartButton: Boolean get() = !addToCartInProgress
        val enableAddToCartButton: Boolean get() = !productWithCartInfo.isInCart
        val addToCartTextRes: Int
            get() = if (productWithCartInfo.isInCart) {
                R.string.catalog_in_cart
            } else {
                R.string.catalog_add_to_cart
            }
    }

}