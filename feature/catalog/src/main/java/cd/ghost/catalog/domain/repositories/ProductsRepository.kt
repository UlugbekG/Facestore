package cd.ghost.catalog.domain.repositories

import cd.ghost.catalog.domain.entity.ProductEntity

interface ProductsRepository {

    suspend fun getAllProducts(sort: String?, limit: Int): List<ProductEntity>

    suspend fun getProductsByCategory(
        category: String?,
        sort: String?,
        limit: Int
    ): List<ProductEntity>

    suspend fun getProductById(productId: Int): ProductEntity

}