package cd.ghost.data.repositories

import android.content.res.Resources.NotFoundException
import cd.ghost.common.Container
import cd.ghost.source.carts.entity.CartItemSourceEntity
import kotlinx.coroutines.flow.Flow

interface   CartDataRepository {

    fun getCart(): Flow<Container<List<CartItemSourceEntity>>>

    /**
     * Add a new product to the cart.
     * @throws NotFoundException
     */
    suspend fun addToCart(productId: Int, quantity: Int)

    /**
     * Get cart item by its ID.
     * @throws NotFoundException
     */
    suspend fun getCartItemById(id: Int): CartItemSourceEntity

    /**
     * Delete the specified cart items.
     */
    suspend fun deleteCartItem(ids: List<Int>)

    /**
     * Delete all items in the cart.
     */
    suspend fun deleteAll()

    /**
     * Change quantity of the specified cart item.
     * @throws NotFoundException
     */
    suspend fun changeQuantity(cartId: Int, quantity: Int)

    /**
     * Reload the flow returned by [getCart]
     */
    fun reload()

}