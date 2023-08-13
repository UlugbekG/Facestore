package cd.ghost.cart.domain.repos

import cd.ghost.cart.domain.entity.CartItem
import cd.ghost.common.Container
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCart(): Flow<Container<List<CartItem>>>

    suspend fun changeQuantity(cartItem: CartItem, newQuantity: Int)

    suspend fun deleteCartItems(cartItemIds: List<Int>)

    suspend fun getCartItemById(cartId: Int): CartItem

}