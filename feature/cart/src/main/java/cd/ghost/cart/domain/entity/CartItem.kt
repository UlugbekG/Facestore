package cd.ghost.cart.domain.entity

import java.math.BigDecimal

data class CartItem(
    val id: Int,
    val product: Product,
    val quantity: Int,
) {

    val totalPrice: String?
        get() {
            return try {

                BigDecimal(product.price)
                    .multiply(BigDecimal(quantity))
                    ?.toString()

            } catch (e: Exception) {
                null
            }
        }
}