package cd.ghost.cart.presentation.cartlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.ghost.cart.R
import cd.ghost.cart.domain.ChangeCartItemQuantityUseCase
import cd.ghost.cart.domain.GetCartItemsUseCase
import cd.ghost.cart.domain.entity.Cart
import cd.ghost.cart.domain.exception.QuantityOutOfRangeException
import cd.ghost.common.Container
import cd.ghost.common.MutableLiveEvent
import cd.ghost.common.asLiveData
import cd.ghost.common.publish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val changeCartItemQuantityUseCase: ChangeCartItemQuantityUseCase
) : ViewModel(), CartAdapterOnClickListener {

    private val selectionModeFlow = MutableStateFlow<SelectionMode>(SelectionMode.Disabled)
    private val cartFlow = MutableStateFlow<Container<Cart>>(Container.Pending)
    val state = combine(
        selectionModeFlow,
        cartFlow,
        ::merge
    )

    private val _message = MutableLiveEvent<Int>()
    val message = _message.asLiveData()

    init {
        viewModelScope.launch {
            getCartItemsUseCase()
                .catch {
                    _message.publish(R.string.error_messsage)
                }
                .collectLatest {
                    cartFlow.value = it
                }
        }
    }

    private fun merge(
        selectionMode: SelectionMode,
        cartContainer: Container<Cart>
    ): Container<State> {
        val showCheckbox = selectionMode is SelectionMode.Enabled
        val countOfSelectedItems = if (selectionMode is SelectionMode.Enabled) {
            selectionMode.identifiers.size
        } else {
            0
        }
        return cartContainer.map { cart ->
            State(
                totalPrice = cart.totalPrice ?: "0",
                cartItems = cart.items.map {
                    val isChecked = selectionMode is SelectionMode.Enabled
                            && selectionMode.identifiers.contains(it.productId)
                    UiCartItem(
                        origin = it,
                        showCheckbox = showCheckbox,
                        isChecked = isChecked,
                        minQuantity = 1,
                        maxQuantity = it.product.count?.toInt() ?: 0
                    )
                },
                enableCreateOrderButton = cart.items.isNotEmpty(),
                showDeleteAction = countOfSelectedItems > 0,
                showDetailsAction = countOfSelectedItems == 1,
                showEditQuantityAction = countOfSelectedItems == 1,
            )
        }
    }

    override fun onItemClick(cartItem: UiCartItem) {

    }

    override fun onIncrementClick(cartItem: UiCartItem) {
        viewModelScope.launch {
            try {
                changeCartItemQuantityUseCase.incrementQuantity(cartItem.origin)
            } catch (e: QuantityOutOfRangeException) {
                _message.publish(R.string.quantity_out_of_range)
            }
        }
    }

    override fun onDecrementClick(cartItem: UiCartItem) {
        viewModelScope.launch {
            try {
                changeCartItemQuantityUseCase.decrementQuantity(cartItem.origin)
            } catch (e: Exception) {
                _message.publish(R.string.quantity_out_of_range)
            }
        }
    }

    override fun onToggle(cartItem: UiCartItem) {

    }

    sealed class SelectionMode {
        object Disabled : SelectionMode()
        class Enabled(
            val identifiers: MutableSet<Int> = mutableSetOf()
        ) : SelectionMode()
    }

    data class State(
        val cartItems: List<UiCartItem>,
        val totalPrice: String,
        val enableCreateOrderButton: Boolean,
        val showDeleteAction: Boolean,
        val showDetailsAction: Boolean,
        val showEditQuantityAction: Boolean,
    ) {
        val showActionsPanel: Boolean
            get() = showDeleteAction || showEditQuantityAction || showDetailsAction
    }
}