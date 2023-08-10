package cd.ghost.fakestore.main.repos

import cd.ghost.common.IoDispatcher
import cd.ghost.data.CartDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartItemsCountRepository @Inject constructor(
    private val cartCartDataRepository: CartDataRepository,
    @IoDispatcher private val ioDispatchers: CoroutineDispatcher
) {

    fun getCartItemsCount(): Flow<Int> {
        return cartCartDataRepository
            .getCart()
            .map { it.size }
            .flowOn(ioDispatchers)
    }

}