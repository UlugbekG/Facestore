package cd.ghost.catalog.domain.repos

import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getProductIdsInCart(): Flow<Set<Int?>>

    /**
     * Add a new product to the cart.
     */
    suspend fun addToCart(productId: Int)
}