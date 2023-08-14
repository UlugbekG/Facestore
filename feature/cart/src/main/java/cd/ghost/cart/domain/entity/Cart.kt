package cd.ghost.cart.domain.entity

import java.math.BigDecimal

data class Cart(
    val items: List<CartItem>
) {
    val totalPrice: String?
        get() {
            return try {
                var price = "0.0"
                items.forEach {
                    val tempt = BigDecimal(price)
                        .plus(BigDecimal(it.totalPrice))
                        .toString()
                    price = tempt
                }
                price
            } catch (e: Exception) {
                null
            }
        }

}