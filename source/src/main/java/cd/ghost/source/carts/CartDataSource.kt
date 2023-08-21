package cd.ghost.source.carts

import cd.ghost.source.carts.entity.CartItemDataEntity

interface CartDataSource {

    suspend fun clearCart()

    suspend fun getCart(): List<CartItemDataEntity>

    suspend fun saveToCart(productId: Int, quantity: Int)

    suspend fun delete(cartItemId: Int)

    suspend fun deleteAll()

}