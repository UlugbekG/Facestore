package cd.ghost.catalog.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.ghost.catalog.R
import cd.ghost.catalog.domain.AddToCartUseCase
import cd.ghost.catalog.domain.GetProductDetailUseCase
import cd.ghost.catalog.domain.ManageFavoritesUseCase
import cd.ghost.catalog.domain.entity.ProductWithInfo
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
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val manageFavoritesUseCase: ManageFavoritesUseCase,
) : ViewModel() {

    private val addToCartInProgressFlow = MutableStateFlow(false)
    private val productFlow = MutableStateFlow<Container<ProductWithInfo>>(Container.Pending)
    val state = combine(
        productFlow,
        addToCartInProgressFlow,
        ::merge
    )

    fun getProduct(productId: Int?) {
        viewModelScope.launch {
            getProductDetailUseCase.getProduct(productId)
                .collectLatest {
                    productFlow.value = it
                }
        }
    }

    fun favoriteAction() {
        viewModelScope.launch {
            val state = state.first().getOrNull() ?: return@launch
            if (state.isInFavorite) {
                manageFavoritesUseCase.remove(state.product)
            } else {
                manageFavoritesUseCase.save(state.product)
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
        productWithInfo: Container<ProductWithInfo>,
        isAddToCartInProgress: Boolean,
    ): Container<State> {
        return productWithInfo.map { productWithCartInfo ->
            State(
                productWithInfo = productWithCartInfo,
                addToCartInProgress = isAddToCartInProgress,
            )
        }
    }

    data class State(
        private val productWithInfo: ProductWithInfo,
        private val addToCartInProgress: Boolean,
    ) {
        val product = productWithInfo.product
        val showAddToCartProgress: Boolean get() = addToCartInProgress
        val showAddToCartButton: Boolean get() = !addToCartInProgress
        val enableAddToCartButton: Boolean get() = !productWithInfo.isInCart
        val isInFavorite: Boolean get() = productWithInfo.favorite

        val addToCartTextRes: Int
            get() = if (productWithInfo.isInCart) {
                R.string.catalog_in_cart
            } else {
                R.string.catalog_add_to_cart
            }
    }

}