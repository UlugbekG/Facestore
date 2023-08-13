package cd.ghost.cart.domain

import cd.ghost.cart.domain.entity.CartItem
import cd.ghost.cart.domain.entity.Product
import cd.ghost.cart.domain.exception.QuantityOutOfRangeException
import cd.ghost.cart.domain.repos.CartRepository
import javax.inject.Inject

class ChangeCartItemQuantityUseCase @Inject constructor(
    private val cartRepository: CartRepository,
) {

    /**
     * Decrease the quantity of [cartItem] by -1.
     * @throws QuantityOutOfRangeException
     */
    suspend fun incrementQuantity(cartItem: CartItem) {
        val newQuantity = cartItem.quantity + 1
        validateProductCount(cartItem.product, newQuantity)
        cartRepository.changeQuantity(cartItem, newQuantity)
    }

    /**
     * Change the quantity of [cartItem] to the [newQuantity] value.
     * @throws QuantityOutOfRangeException
     */
    suspend fun decrementQuantity(cartItem: CartItem) {
        val newQuantity = cartItem.quantity - 1
        validateProductCount(cartItem.product, newQuantity)
        cartRepository.changeQuantity(cartItem, newQuantity)
    }

    /**
     * @throws QuantityOutOfRangeException
     */
    private fun validateProductCount(product: Product, newQuantity: Int) {
        val quantity = product.count?.toInt() ?: 0
        if (newQuantity < 1 || quantity < newQuantity) {
            throw QuantityOutOfRangeException()
        }
    }
}