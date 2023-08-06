package cd.ghost.data

import cd.ghost.data.sources.products.entities.ResponseProduct

interface DataProductsRepository {

    suspend fun getProductsByCategory(
        category: String?,
        limit: Int?,
        sort: String?
    ): List<ResponseProduct>

    suspend fun getAllProducts(
        limit: Int?,
        sort: String?
    ): List<ResponseProduct>

    suspend fun getProduct(productId: Int): ResponseProduct

    suspend fun getCategories(): List<String>

}