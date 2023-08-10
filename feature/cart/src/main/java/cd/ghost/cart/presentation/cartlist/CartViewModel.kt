package cd.ghost.cart.presentation.cartlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.ghost.cart.domain.GetCartItemsUseCase
import cd.ghost.cart.domain.entity.CartItem
import cd.ghost.common.Container
import cd.ghost.common.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase
) : ViewModel(), CartAdapterOnClickListener {

    private val _cart = MutableLiveData<Container<List<CartItem>>>()
    val cart = _cart.asLiveData()

    fun items() = getCartItemsUseCase()

    init {
        viewModelScope.launch {
            getCartItemsUseCase().collectLatest {
//                _cart.value =
            }
        }
    }

    override fun onItemClick(cartItem: CartItem) {
        TODO("Not yet implemented")
    }

    override fun onIncreaseBtnClick(id: Int, quantity: Int) {
        TODO("Not yet implemented")
    }

    override fun onDecreaseBtnClick(id: Int, quantity: Int) {
        TODO("Not yet implemented")
    }

    override fun onLongClick(cartItem: CartItem) {
        TODO("Not yet implemented")
    }

//    fun addProductToCart(productId: Int, quantity: Int) {
//        viewModelScope.launch {
//            addItemToCartUseCase
//        }
//    }

}