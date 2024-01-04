package cd.ghost.source.orders.entity

data class OrderProductSourceEntity(
    val productId: Int?,
    val quantity: Int,
    val title: String,
    val description: String,
    val thump: String,
    val price: String
)