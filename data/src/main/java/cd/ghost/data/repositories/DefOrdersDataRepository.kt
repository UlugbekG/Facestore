package cd.ghost.data.repositories

import cd.ghost.source.orders.OrdersSource
import cd.ghost.source.orders.entity.OrderProductSourceEntity
import cd.ghost.source.orders.entity.OrderSourceEntity
import javax.inject.Inject

class DefOrdersDataRepository @Inject constructor(
    private val ordersSource: OrdersSource
) : OrdersDataRepository {

    override suspend fun saveOrder(
        firstname: String,
        lastname: String,
        address: String,
        comment: String?,
        items: List<OrderProductSourceEntity>
    ): OrderSourceEntity {
        return ordersSource.saveOrder(
            firstname = firstname,
            lastname = lastname,
            address = address,
            comment = comment,
            items = items
        )
    }

    override suspend fun cancelOrder(orderId: String) {
        ordersSource.cancelOrder(orderId)
    }

    override suspend fun getOrders(): List<OrderSourceEntity> {
        return ordersSource.getOrders()
    }

}