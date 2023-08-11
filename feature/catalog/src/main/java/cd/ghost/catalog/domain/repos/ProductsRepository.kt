package cd.ghost.catalog.domain.repos

import cd.ghost.catalog.domain.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getAllProducts(sort: String?, limit: Int): List<ProductEntity>

    suspend fun getProductsByCategory(
        category: String?,
        sort: String?,
        limit: Int
    ): List<ProductEntity>

    suspend fun getProductById(productId: Int): ProductEntity

    suspend fun getCartItemIds(): Flow<List<Int?>>

}