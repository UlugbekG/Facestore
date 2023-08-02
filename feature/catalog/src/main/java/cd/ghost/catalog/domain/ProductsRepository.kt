package cd.ghost.catalog.domain

import cd.ghost.catalog.domain.entity.EntityProduct

interface ProductsRepository {

    suspend fun getAllProducts(sort: String?): List<EntityProduct>

    suspend fun getProductsByCategory(category: String?, sort: String?): List<EntityProduct>
}