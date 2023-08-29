package cd.ghost.data.repositories

import cd.ghost.data.database.favorites.entity.ProductDataEntity
import kotlinx.coroutines.flow.Flow

interface FavoritesDataRepository {

    fun getProducts(): Flow<List<ProductDataEntity>>

    suspend fun insert(product: ProductDataEntity)

    suspend fun insert(products: List<ProductDataEntity>)

    suspend fun getProduct(id: Int): ProductDataEntity

    suspend fun delete(id: Int)

    suspend fun deleteAll()

}