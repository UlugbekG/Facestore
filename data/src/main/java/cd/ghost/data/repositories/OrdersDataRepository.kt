package cd.ghost.data.repositories

import cd.ghost.source.orders.entity.OrderProductSourceEntity
import cd.ghost.source.orders.entity.OrderSourceEntity

interface OrdersDataRepository {

    suspend fun saveOrder(
        firstname: String,
        lastname: String,
        address: String,
        comment: String?,
        items: List<OrderProductSourceEntity>
    ): OrderSourceEntity

    suspend fun cancelOrder(orderId: String)

    suspend fun getOrders(): List<OrderSourceEntity>

}