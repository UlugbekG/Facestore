package cd.ghost.catalog.domain

import android.util.Log
import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.repositories.CartRepository
import cd.ghost.common.Container
import cd.ghost.common.Core
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(product: ProductEntity) = withTimeout(Core.localTimeoutMillis) {
        val productIdsInCart = cartRepository.getProductIdsInCart()
            .filterIsInstance<Container.Success<Set<Int?>>>()
            .first()
        if (!productIdsInCart.value.contains(product.id)) {
            cartRepository.addToCart(product.id)
        }
    }

}