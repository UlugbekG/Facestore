package cd.ghost.source

import cd.ghost.common.NotFoundException
import cd.ghost.source.orders.OrdersSource
import cd.ghost.source.orders.entity.OrderProductSourceEntity
import cd.ghost.source.orders.entity.OrderSourceEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class DefOrderSource @Inject constructor() : OrdersSource {

    private val orders = mutableListOf<OrderSourceEntity>()

    override suspend fun saveOrder(
        firstname: String,
        lastname: String,
        address: String,
        comment: String?,
        items: List<OrderProductSourceEntity>,
    ): OrderSourceEntity =
        withContext(Dispatchers.IO) {
            val orderId = UUID.randomUUID().toString()
            OrderSourceEntity(
                orderId = orderId,
                itemsList = items,
                firstname = firstname,
                lastname = lastname,
                address = address,
                comment = comment,
                createdDate = System.currentTimeMillis(),
            )
        }


    override suspend fun cancelOrder(orderId: String) {
        withContext(Dispatchers.IO) {
            val index = orders.indexOfFirst { it.orderId == orderId }
            if (index != -1) {
                val orderData = orders[index]
                    .copy(cancelDate = System.currentTimeMillis())
                orders[index] = orderData
            } else {
                throw NotFoundException("Order not found!!!")
            }
        }
    }

    override suspend fun getOrderById(orderId: String): OrderSourceEntity =
        withContext(Dispatchers.IO) {
            orders.find { it.orderId == orderId } ?: throw NotFoundException("Order not found!!!")
        }

    override suspend fun getOrders(): List<OrderSourceEntity> {
        return orders
    }

}