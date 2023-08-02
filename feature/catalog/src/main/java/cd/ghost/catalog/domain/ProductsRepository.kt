package cd.ghost.catalog.domain

import cd.ghost.catalog.domain.entity.EntityProduct

interface ProductsRepository {

    suspend fun getProducts(category: String?, sort: String?): List<EntityProduct>
}