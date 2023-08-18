package cd.ghost.cart.domain

import cd.ghost.cart.domain.entity.CartItem
import cd.ghost.cart.domain.repos.CartRepository
import javax.inject.Inject

class DeleteCartItemUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    /**
     * Delete the list of cart items.
     * @throws NotFoundException
     */
    suspend fun deleteCartItems(cartItems: List<CartItem>) {
        cartRepository.deleteCartItems(cartItems.map { it.id })
    }

}