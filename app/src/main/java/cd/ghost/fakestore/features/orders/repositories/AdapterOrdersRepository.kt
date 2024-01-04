package cd.ghost.fakestore.features.orders.repositories

import cd.ghost.common.Container
import cd.ghost.data.repositories.CartDataRepository
import cd.ghost.data.repositories.OrdersDataRepository
import cd.ghost.fakestore.features.orders.mapper.OrderMapper
import cd.ghost.orders.domain.entity.OrderEntity
import cd.ghost.orders.domain.entity.UserCredential
import cd.ghost.orders.domain.repositories.OrdersRepository
import cd.ghost.source.carts.entity.CartItemSourceEntity
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AdapterOrdersRepository @Inject constructor(
    private val ordersDataRepository: OrdersDataRepository,
    private val cartRepository: CartDataRepository,
    private val orderMapper: OrderMapper
) : OrdersRepository {

    override suspend fun createOrder(credential: UserCredential): OrderEntity {
        // first of all we need to get our chosen products
        val items = cartRepository.getCart()
            .filterIsInstance<Container.Success<List<CartItemSourceEntity>>>()
            .first()
            .getOrNull()
            ?.map {
                orderMapper.toOrderProductSourceEntity(it)
            } ?: emptyList()

        val newOrder = ordersDataRepository.saveOrder(
            firstname = credential.firstname,
            lastname = credential.lastname,
            address = credential.address,
            comment = credential.comment,
            items = items,
        )
        return orderMapper.toOrderEntity(newOrder)
    }

    override suspend fun getOrder(orderId: String): OrderEntity {
        TODO("Not yet implemented")
    }

    override suspend fun ordersList(): List<OrderEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun cancelOrder() {
        TODO("Not yet implemented")
    }

}