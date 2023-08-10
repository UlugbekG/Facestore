package cd.ghost.catalog.domain

import cd.ghost.catalog.domain.repos.ProductsRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    suspend operator fun invoke(productId: Int) {
        productsRepository.addToCart(productId)
    }
}