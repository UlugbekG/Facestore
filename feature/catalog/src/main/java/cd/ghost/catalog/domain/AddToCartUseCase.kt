package cd.ghost.catalog.domain

import android.util.Log
import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.repos.CartRepository
import cd.ghost.common.Container
import cd.ghost.common.Core
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import kotlin.math.log

class AddToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    private val TAG = "AddToCartUseCase"

    suspend operator fun invoke(product: ProductEntity) = withTimeout(Core.localTimeoutMillis) {
        val productIdsInCart = cartRepository.getProductIdsInCart()
            .filterIsInstance<Container.Success<Set<Int?>>>()
            .first()
        Log.d(TAG, "invoke: ${productIdsInCart.value.contains(product.id)}")
        if (!productIdsInCart.value.contains(product.id)) {
            Log.d(TAG, "invoke: Should work")
            cartRepository.addToCart(product.id)
        }
    }

}