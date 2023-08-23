package cd.ghost.fakestore.features.favorites

import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.repositories.FavoritesRepository
import cd.ghost.data.repositories.FavoritesDataRepository
import cd.ghost.fakestore.features.favorites.mapper.ProductsMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefFavoritesRepository @Inject constructor(
    private val favoritesDataRepository: FavoritesDataRepository,
    private val productMapper: ProductsMapper
) : FavoritesRepository {

    override fun getProducts(): Flow<List<ProductEntity>> {
        return favoritesDataRepository.getProducts().map { list ->
            list.map { productMapper.toProductEntity(it) }
        }
    }

    override suspend fun save(product: ProductEntity) {
        val value = productMapper.toProductDataEntity(product)
        favoritesDataRepository.insert(value)
    }

    override suspend fun remove(id: Int) {
        favoritesDataRepository.delete(id)
    }
}