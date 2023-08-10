package cd.ghost.data

import android.content.res.Resources.NotFoundException
import cd.ghost.data.sources.carts.entity.CartItemDataEntity
import kotlinx.coroutines.flow.StateFlow

interface CartDataRepository {

    fun getCart(): StateFlow<List<CartItemDataEntity>>

    /**
     * Find cart item by its id
     * @throws NotFoundException
     */
    suspend fun getCartItemById(cartId: Int): CartItemDataEntity

    suspend fun addToCart(productId: Int)

    /**
     * Change cart item quantity
     * @throws NotFoundException
     */
    suspend fun changeQuantity(cartId: Int, quantity: Int)

    suspend fun delete(productId: Int)

    suspend fun clear()

}