package cd.ghost.orders.domain.repositories

import cd.ghost.orders.domain.entity.OrderEntity
import cd.ghost.orders.domain.entity.UserCredential

interface OrdersRepository {

    suspend fun createOrder(credential: UserCredential): OrderEntity

    suspend fun getOrder(orderId: String): OrderEntity

    suspend fun ordersList(): List<OrderEntity>

    suspend fun cancelOrder()

}