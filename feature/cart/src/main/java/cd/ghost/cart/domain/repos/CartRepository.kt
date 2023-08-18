package cd.ghost.cart.domain.repos

import cd.ghost.cart.domain.entity.CartItem
import cd.ghost.common.Container
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    /**
     * Change the quantity of [cartItemId] to the [newQuantity] value.
     * @throws NotFoundException
     */
    suspend fun changeQuantity(cartItemId: Int, newQuantity: Int)

    /**
     * Delete the specified cart items.
     * @throws NotFoundException
     */
    suspend fun deleteCartItems(cartItemIds: List<Int>)

    /**
     * Get cart item by ID.
     * @throws NotFoundException
     */
    suspend fun getCartItemById(id: Int): CartItem

    /**
     * Listen for user's Cart.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getCartItems(): Flow<Container<List<CartItem>>>

    /**
     * Reload Cart flow returned by [getCartItems].
     */
    fun reload()

}