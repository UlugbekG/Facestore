package cd.ghost.source.orders

import cd.ghost.source.orders.entity.OrderProductSourceEntity
import cd.ghost.source.orders.entity.OrderSourceEntity

interface OrdersSource {

    suspend fun saveOrder(
        firstname: String,
        lastname: String,
        address: String,
        comment: String?,
        items: List<OrderProductSourceEntity>,
    ): OrderSourceEntity

    suspend fun cancelOrder(orderId: String)

    suspend fun getOrderById(orderId: String): OrderSourceEntity

    suspend fun getOrders(): List<OrderSourceEntity>

}