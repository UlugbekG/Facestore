package cd.ghost.catalog.domain

import cd.ghost.catalog.domain.entity.ProductWithCartInfo
import cd.ghost.catalog.domain.repos.CartRepository
import cd.ghost.catalog.domain.repos.ProductsRepository
import cd.ghost.common.Container
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val repository: ProductsRepository,
    private val cartRepository: CartRepository
) {

    fun getProduct(productId: Int): Flow<Container<ProductWithCartInfo>> {
        return cartRepository.getProductIdsInCart()
            .map { container ->
                container.suspendMap { idsInCart ->
                    ProductWithCartInfo(
                        product = repository.getProductById(productId),
                        isInCart = idsInCart.contains(productId)
                    )
                }
            }
    }

    /**
     * Reload product flow returned by [getProduct]
     */
    fun reload() {
        cartRepository.reload()
    }

}