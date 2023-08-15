package cd.ghost.cart.presentation.cartlist.entity

import cd.ghost.cart.domain.entity.CartItem
import cd.ghost.cart.domain.entity.Product
import java.math.BigDecimal

data class UiCartItem(
    val origin: CartItem,
    val isChecked: Boolean,
    val showCheckbox: Boolean,
    val minQuantity: Int,
    val maxQuantity: Int
) {

    val id: Int get() = origin.productId

    val product: Product get() = origin.product

    val imageUrl: String? get() = product.imageUrl

    val quantity: Int get() = origin.quantity

    val name: String? get() = product.title

    val totalOriginPrice: String?
        get() {
            return try {

                BigDecimal(product.price)
                    .multiply(BigDecimal(quantity))
                    ?.toString()

            } catch (e: Exception) {
                null
            }
        }

    val canIncrement: Boolean get() = quantity < maxQuantity

    val canDecrement: Boolean get() = quantity > minQuantity
}