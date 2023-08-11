package cd.ghost.catalog.domain

import android.util.Log
import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.repos.CartRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(product: ProductEntity) {
        val productIdsInCart = cartRepository.getProductIdsInCart().first()
        if (!productIdsInCart.contains(product.id)) {
            cartRepository.addToCart(product.id!!)
        }
    }

}