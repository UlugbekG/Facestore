package cd.ghost.data

import android.content.res.Resources.NotFoundException
import cd.ghost.common.Container
import cd.ghost.data.sources.carts.entity.CartItemDataEntity
import kotlinx.coroutines.flow.Flow

interface CartDataRepository {

    fun getCart(): Flow<Container<List<CartItemDataEntity>>>

    /**
     * Add a new product to the cart.
     * @throws NotFoundException
     */
    suspend fun addToCart(productId: Int, quantity: Int)

    /**
     * Get cart item by its ID.
     * @throws NotFoundException
     */
    suspend fun getCartItemById(id: Int): CartItemDataEntity

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