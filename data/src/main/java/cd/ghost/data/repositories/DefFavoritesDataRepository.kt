package cd.ghost.data.repositories

import cd.ghost.common.IoDispatcher
import cd.ghost.data.database.FavoritesDao
import cd.ghost.data.database.entity.ProductDataEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefFavoritesDataRepository @Inject constructor(
    private val favoritesDao: FavoritesDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FavoritesDataRepository {

    override fun getProducts(): Flow<List<ProductDataEntity>> {
        return favoritesDao.getAllProducts()
    }

    override suspend fun insert(product: ProductDataEntity) {
        withContext(ioDispatcher) {
            favoritesDao.insert(product)
        }
    }

    override suspend fun insert(products: List<ProductDataEntity>) {
        withContext(ioDispatcher) {
            favoritesDao.insert(products)
        }
    }

    override suspend fun getProduct(id: Int): ProductDataEntity =
        withContext(ioDispatcher) {
            favoritesDao.getProduct(id)
        }

    override suspend fun delete(id: Int) {
        withContext(ioDispatcher) {
            favoritesDao.delete(id)
        }
    }

    override suspend fun deleteAll() {
        withContext(ioDispatcher) {
            favoritesDao.deleteAll()
        }
    }

}