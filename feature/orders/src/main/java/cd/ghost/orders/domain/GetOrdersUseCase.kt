package cd.ghost.orders.domain

import cd.ghost.orders.domain.repositories.OrdersRepository
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(
    private val ordersRepository: OrdersRepository
) {

    suspend fun getList() = ordersRepository.ordersList()

}