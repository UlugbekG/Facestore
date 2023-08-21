package cd.ghost.fakestore.main.repos

import cd.ghost.common.Container
import cd.ghost.data.repositories.CartDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartItemsCountRepository @Inject constructor(
    private val cartCartDataRepository: CartDataRepository,
) {

    fun getCartItemsCount(): Flow<Container<Int>> {
        return cartCartDataRepository.getCart().map { container ->
            container.map { it.size }
        }
    }

}