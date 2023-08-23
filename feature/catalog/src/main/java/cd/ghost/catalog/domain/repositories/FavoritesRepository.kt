package cd.ghost.catalog.domain.repositories

import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.common.Container
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun getProducts(): Flow<List<ProductEntity>>

    suspend fun save(product: ProductEntity)

    suspend fun remove(id: Int)

}