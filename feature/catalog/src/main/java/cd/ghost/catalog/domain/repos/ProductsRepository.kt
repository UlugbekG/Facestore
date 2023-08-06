package cd.ghost.catalog.domain.repos

import cd.ghost.catalog.domain.entity.EntityProduct

interface ProductsRepository {

    suspend fun getAllProducts(sort: String?, limit: Int): List<EntityProduct>

    suspend fun getProductsByCategory(category: String?, sort: String?, limit:Int): List<EntityProduct>
}