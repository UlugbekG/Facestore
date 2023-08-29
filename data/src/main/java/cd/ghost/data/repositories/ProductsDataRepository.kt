package cd.ghost.data.repositories

import cd.ghost.common.Container
import cd.ghost.source.products.entities.ResponseProduct


interface ProductsDataRepository {

    suspend fun getProductsByCategory(
        category: String?,
        limit: Int?,
        sort: String?
    ): Container<List<ResponseProduct>>

    suspend fun getAllProducts(
        limit: Int?,
        sort: String?
    ): Container<List<ResponseProduct>>

    suspend fun getProduct(productId: Int): ResponseProduct

    suspend fun getCategories(): Container<List<String>>

}