package cd.ghost.cart.domain

import cd.ghost.cart.domain.entity.Cart
import cd.ghost.cart.domain.entity.CartItem
import cd.ghost.cart.domain.repos.CartRepository
import cd.ghost.common.Container
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
) {


    /**
     * Listen for user's Cart.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getCart(): Flow<Container<Cart>> {
        return cartRepository.getCartItems().map { container ->
            container.map {
                Cart(it)
            }
        }
    }

    /**
     * Get cart item by ID.
     * @throws NotFoundException
     */
    suspend fun getCartById(cartItemId: Int): CartItem {
        return cartRepository.getCartItemById(cartItemId)
    }

    /**
     * Reload Cart flow returned by [getCart].
     */
    fun reload() {
        cartRepository.reload()
    }
}