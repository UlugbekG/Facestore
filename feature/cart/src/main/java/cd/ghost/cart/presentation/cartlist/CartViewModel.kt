package cd.ghost.cart.presentation.cartlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.ghost.cart.R
import cd.ghost.cart.domain.ChangeCartItemQuantityUseCase
import cd.ghost.cart.domain.GetCartItemsUseCase
import cd.ghost.cart.domain.entity.Cart
import cd.ghost.cart.domain.entity.CartItem
import cd.ghost.cart.domain.exception.QuantityOutOfRangeException
import cd.ghost.common.Container
import cd.ghost.common.MutableLiveEvent
import cd.ghost.common.asLiveData
import cd.ghost.common.publish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val changeCartItemQuantityUseCase: ChangeCartItemQuantityUseCase
) : ViewModel(), CartAdapterOnClickListener {

    private val _cart = MutableLiveData<Container<Cart>>()
    val cart = _cart.asLiveData()

    private val _message = MutableLiveEvent<Int>()
    val message = _message.asLiveData()

    init {
        viewModelScope.launch {
            getCartItemsUseCase()
                .catch {
                    _message.publish(R.string.error_messsage)
                }
                .collectLatest {
                    _cart.value = it
                }
        }
    }

    override fun onItemClick(cartItem: CartItem) {

    }

    override fun onIncrementClick(cartItem: CartItem) {
        viewModelScope.launch {
            try {
                changeCartItemQuantityUseCase.incrementQuantity(cartItem)
            } catch (e: QuantityOutOfRangeException) {
                _message.publish(R.string.quantity_out_of_range)
            }
        }
    }

    override fun onDecrementClick(cartItem: CartItem) {
        viewModelScope.launch {
            try {
                changeCartItemQuantityUseCase.decrementQuantity(cartItem)
            } catch (e: Exception) {
                _message.publish(R.string.quantity_out_of_range)
            }
        }
    }

    override fun onLongClick(cartItem: CartItem) {

    }
}