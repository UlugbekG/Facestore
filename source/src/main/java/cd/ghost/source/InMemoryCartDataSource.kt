package cd.ghost.source

import cd.ghost.source.carts.CartDataSource
import cd.ghost.source.carts.entity.CartItemDataEntity
import javax.inject.Inject

class InMemoryCartDataSource @Inject constructor() : CartDataSource {

    private val cart = mutableListOf<CartItemDataEntity>()

    override suspend fun clearCart() {
        cart.clear()
    }

    override suspend fun getCart(): List<CartItemDataEntity> {
        return ArrayList(cart)
    }

    override suspend fun saveToCart(productId: Int, quantity: Int) {
        val index = cart.indexOfFirst { it.productId == productId }
        val cartItem = CartItemDataEntity(
            productId, productId, quantity
        )
        if (index != -1) {
            cart[index] = cartItem
        } else {
            cart.add(cartItem)
        }
    }

    override suspend fun delete(cartItemId: Int) {
        cart.removeAll { it.id == cartItemId }
    }

    override suspend fun deleteAll() {
        cart.clear()
    }
}