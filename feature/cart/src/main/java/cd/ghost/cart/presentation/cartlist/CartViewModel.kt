package cd.ghost.cart.presentation.cartlist

import cd.ghost.cart.R
import cd.ghost.cart.domain.ChangeCartItemQuantityUseCase
import cd.ghost.cart.domain.GetCartItemsUseCase
import cd.ghost.cart.domain.entity.Cart
import cd.ghost.cart.presentation.CartRouter
import cd.ghost.cart.presentation.cartlist.entity.UiCartItem
import cd.ghost.common.Container
import cd.ghost.presentation.BaseViewModel
import cd.ghost.presentation.live.MutableLiveEvent
import cd.ghost.presentation.live.asLiveData
import cd.ghost.presentation.live.publish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val changeCartItemQuantityUseCase: ChangeCartItemQuantityUseCase,
    private val router: CartRouter,
) : BaseViewModel(), CartAdapterOnClickListener {

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
                    _message.publish(R.string.error_message)
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
            selectionMode.selectedIds.size
        } else {
            0
        }
        return cartContainer.map { cart ->
            State(
                totalPrice = cart.totalPrice ?: "0",
                cartItems = cart.items.map {
                    val isChecked = selectionMode is SelectionMode.Enabled
                            && selectionMode.selectedIds.contains(it.productId)
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
                onBackPressEnabled = showCheckbox
            )
        }
    }

    override fun onItemClick(cartItem: UiCartItem) {

    }

    override fun onIncrementClick(cartItem: UiCartItem) = debounce {
        viewModelScope.launch {
            changeCartItemQuantityUseCase.incrementQuantity(cartItem.origin)
        }
    }

    override fun onDecrementClick(cartItem: UiCartItem) = debounce {
        viewModelScope.launch {
            changeCartItemQuantityUseCase.decrementQuantity(cartItem.origin)
        }
    }

    override fun onToggle(cartItem: UiCartItem) = debounce {
        val selectionMode = selectionModeFlow.value
        if (selectionMode is SelectionMode.Enabled) {
            val selectedIds = selectionMode.selectedIds
            if (selectedIds.contains(cartItem.id)) {
                selectedIds.remove(cartItem.id)
            } else {
                selectedIds.add(cartItem.id)
            }
            selectionModeFlow.value = SelectionMode.Enabled(selectedIds)
        } else {
            selectionModeFlow.value = SelectionMode.Enabled(mutableSetOf(cartItem.id))
        }
    }

    fun disableSelectionMode() {
        selectionModeFlow.value = SelectionMode.Disabled
    }


    sealed class SelectionMode {
        object Disabled : SelectionMode()
        class Enabled(
            val selectedIds: MutableSet<Int> = mutableSetOf()
        ) : SelectionMode()
    }

    data class State(
        val cartItems: List<UiCartItem>,
        val totalPrice: String,
        val enableCreateOrderButton: Boolean,
        val showDeleteAction: Boolean,
        val showDetailsAction: Boolean,
        val showEditQuantityAction: Boolean,
        val onBackPressEnabled: Boolean,
    ) {
        val showActionsPanel: Boolean
            get() = showDeleteAction || showEditQuantityAction || showDetailsAction
    }
}