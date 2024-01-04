package cd.ghost.orders.domain

import cd.ghost.orders.domain.entity.UserCredential
import cd.ghost.orders.domain.exceptions.EmptyException
import cd.ghost.orders.domain.exceptions.ExceptionType
import cd.ghost.orders.domain.repositories.OrdersRepository
import javax.inject.Inject

class CreateOrderUseCase @Inject constructor(
    private val ordersRepository: OrdersRepository
) {

    suspend operator fun invoke(
        firstname: String?,
        lastname: String?,
        address: String?,
        comment: String?
    ) {
        if (firstname.isNullOrBlank()) throw EmptyException(ExceptionType.FIRSTNAME)
        if (lastname.isNullOrBlank()) throw EmptyException(ExceptionType.LASTNAME)
        if (address.isNullOrBlank()) throw EmptyException(ExceptionType.ADDRESS)
        ordersRepository.createOrder(UserCredential(firstname, lastname, address, comment))
    }
}