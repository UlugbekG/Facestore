package cd.ghost.fakestore.features.catalog.repos

import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.repos.CartRepository
import cd.ghost.data.CartDataRepository
import cd.ghost.fakestore.features.catalog.mapper.CatalogProductMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultCartRepository @Inject constructor(
    private val cartDataRepository: CartDataRepository,
    private val catalogProductMapper: CatalogProductMapper
) : CartRepository {
    override fun getProductIdsInCart(): Flow<Set<Int?>> {
        return cartDataRepository.getCart().map { items ->
            items.map { it.productId }.toSet()
        }
    }

    override suspend fun addToCart(product: ProductEntity) {
        cartDataRepository.newCartItem(
            product = catalogProductMapper.toProductDataEntity(product)
        )
    }
}