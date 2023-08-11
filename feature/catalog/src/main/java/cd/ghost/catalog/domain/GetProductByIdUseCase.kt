package cd.ghost.catalog.domain

import cd.ghost.catalog.domain.entity.ProductWithCartInfo
import cd.ghost.catalog.domain.repos.CartRepository
import cd.ghost.catalog.domain.repos.ProductsRepository
import cd.ghost.common.Container
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductsRepository,
    private val cartRepository: CartRepository
) {

    operator fun invoke(productId: Int): Flow<Container<ProductWithCartInfo>> {
        return cartRepository.getProductIdsInCart().map { identifiers ->
            try {
                val product = repository.getProductById(
                    productId = productId
                )
                val productWithCartInfo = ProductWithCartInfo(
                    product = product,
                    isInCart = identifiers.contains(productId)
                )
                Container.Success(productWithCartInfo)
            } catch (e: Exception) {
                Container.Error(e)
            }
        }
    }

}