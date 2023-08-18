package cd.ghost.catalog.domain.repos

import cd.ghost.common.Container
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getProductIdsInCart(): Flow<Container<Set<Int?>>>

    /**
     * Add a new product to the cart.
     */
    suspend fun addToCart(productId: Int)

    /**
     * Reload the flow returned by [getProductIdentifiersInCart]
     */
    fun reload()

}