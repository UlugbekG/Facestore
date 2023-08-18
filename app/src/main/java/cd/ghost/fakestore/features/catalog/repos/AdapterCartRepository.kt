package cd.ghost.fakestore.features.catalog.repos

import cd.ghost.catalog.domain.repos.CartRepository
import cd.ghost.common.Container
import cd.ghost.data.CartDataRepository
import cd.ghost.fakestore.features.catalog.mapper.CatalogProductMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdapterCartRepository @Inject constructor(
    private val cartDataRepository: CartDataRepository,
) : CartRepository {

    override fun getProductIdsInCart(): Flow<Container<Set<Int?>>> {
        return cartDataRepository.getCart().map { container ->
            container.map { list ->
                list.map { it.productId }.toSet()
            }
        }
    }

    override suspend fun addToCart(productId: Int) {
        cartDataRepository.addToCart(productId, quantity = 1)
    }

    override fun reload() {
        cartDataRepository.reload()
    }

}