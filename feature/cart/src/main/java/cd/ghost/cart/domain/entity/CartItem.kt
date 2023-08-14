package cd.ghost.cart.domain.entity

import java.math.BigDecimal

data class CartItem(
    val cartId: Int,
    val productId: Int,
    val quantity: Int,
    val product: Product
) {

    val totalPrice: String?
        get() {
            return try {

                BigDecimal(product.price)
                    .multiply(BigDecimal(quantity))
                    .toString()

            } catch (e: Exception) {
                null
            }
        }
}