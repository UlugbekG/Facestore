package cd.ghost.cart.domain

import cd.ghost.cart.domain.entity.CartItem
import cd.ghost.cart.domain.exception.QuantityOutOfRangeException
import cd.ghost.cart.domain.repos.ProductsRepository
import javax.inject.Inject

class ValidateCartItemQuantityUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    /**
     * Validate a new quantity for the specified [cartItem].
     * @throws NotFoundException
     * @throws QuantityOutOfRangeException
     */
    suspend fun validateNewQuantity(cartItem: CartItem, newQuantity: Int) {
        if (newQuantity > getMaxQuantity(cartItem)) throw QuantityOutOfRangeException()
        if (newQuantity < 1) throw QuantityOutOfRangeException()
    }

    /**
     * Get max available quantity for the specified [cartItem]
     * @throws NotFoundException
     */
    private suspend fun getMaxQuantity(cartItem: CartItem): Int {
        return productsRepository.getAvailableQuantity(cartItem.product.id) ?: 0
    }

}