package cd.ghost.orders.domain.entity

data class OrderEntity(
    val orderId: String,
    val firstname: String,
    val lastname: String,
    val status: OrderStatus,
    val address: String,
    val comment: String?,
    val products: List<ProductEntity>,
    val cancelDate: String? = null,
    val createdDate: String?
)
