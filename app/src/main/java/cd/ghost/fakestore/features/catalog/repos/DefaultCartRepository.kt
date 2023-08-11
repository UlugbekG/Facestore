package cd.ghost.fakestore.features.catalog.repos

import cd.ghost.catalog.domain.repos.CartRepository
import cd.ghost.common.IoDispatcher
import cd.ghost.data.CartDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultCartRepository @Inject constructor(
    private val cartDataRepository: CartDataRepository,
    @IoDispatcher private val ioDispatchers: CoroutineDispatcher,
) : CartRepository {
    override fun getProductIdsInCart(): Flow<Set<Int?>> {
        return cartDataRepository.getCart().map { items ->
            items.map { it.productId }.toSet()
        }
    }

    override suspend fun addToCart(productId: Int) =
        withContext(ioDispatchers) {
            cartDataRepository.addToCart(productId)
        }
}