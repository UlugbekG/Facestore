package cd.ghost.cart.domain

import cd.ghost.cart.domain.entity.Cart
import cd.ghost.cart.domain.repos.CartRepository
import cd.ghost.common.Container
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    operator fun invoke(): Flow<Container<Cart>> {
        return cartRepository.getCart().map { container ->
            container.map { Cart(it) }
        }
    }

}