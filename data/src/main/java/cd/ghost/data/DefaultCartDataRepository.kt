package cd.ghost.data

import android.content.res.Resources.NotFoundException
import android.util.Log
import cd.ghost.data.sources.carts.entity.CartItemDataEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DefaultCartDataRepository @Inject constructor(

) : CartDataRepository {

    private val TAG = "DefaultCartDataReposito"

    private val _cart = MutableStateFlow<List<CartItemDataEntity>>(emptyList())
    override fun getCart(): StateFlow<List<CartItemDataEntity>> = _cart

    override suspend fun addToCart(productId: Int) {
        val list = ArrayList(_cart.value)
        val newItem = CartItemDataEntity(productId, 1)
        list.add(newItem)
        _cart.value = list
    }

    override suspend fun getCartItemById(cartId: Int): CartItemDataEntity {
        val list = ArrayList(_cart.value)
        val cart = list.find { it.productId == cartId }
        return cart ?: throw NotFoundException()
    }

    override suspend fun changeQuantity(cartId: Int, quantity: Int) {
        val list = ArrayList(_cart.value)
        val index = list.indexOfFirst { it.productId == cartId }
        val cartItem = CartItemDataEntity(cartId, quantity)
        if (index != -1) {
            list[index] = cartItem
        } else {
            list.add(cartItem)
        }
        _cart.value = list
    }

    override suspend fun clear() {
        _cart.value = emptyList()
    }

    override suspend fun delete(productId: Int) {
        val list = ArrayList(_cart.value)
        list.removeAll { it.productId == productId }
        _cart.value = list
    }
}