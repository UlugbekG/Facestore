package cd.ghost.orders.domain.entity

data class ProductEntity(
    val productId: Int?,
    val quantity: Int,
    val thump: String,
    val title: String,
    val description: String,
    val price: String
)