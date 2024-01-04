package cd.ghost.source.orders.entity

data class OrderSourceEntity(
    val orderId: String,
    val itemsList: List<OrderProductSourceEntity>,
    val firstname: String,
    val lastname: String,
    val comment: String?,
    val address: String,
    val createdDate: Long?,
    /** if order cancelDate cancel date should not be null */
    val cancelDate: Long? = null,
)