package cd.ghost.data

import cd.ghost.data.sources.products.entities.ResponseProduct

interface DataProductsRepository {

    suspend fun getAllProducts(
        category: String?,
        limit: Int?,
        sort: String?
    ): List<ResponseProduct>

    suspend fun getProduct(): ResponseProduct

    suspend fun getCategory(): List<String>

}