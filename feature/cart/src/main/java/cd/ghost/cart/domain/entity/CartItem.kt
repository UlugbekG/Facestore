package cd.ghost.cart.domain.entity

data class CartItem(
    val cartId: Int,
    val productId: Int?,
    val quantity: Int?,
    val product: Product
)