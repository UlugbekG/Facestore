package cd.ghost.fakestore.features.catalog.repos

import android.util.Log
import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.repos.ProductsRepository
import cd.ghost.common.IoDispatcher
import cd.ghost.data.CartDataRepository
import cd.ghost.data.ProductsDataRepository
import cd.ghost.fakestore.features.catalog.mapper.ProductMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultCatalogsRepository @Inject constructor(
    private val repository: ProductsDataRepository,
    private val productMapper: ProductMapper,
    private val cartDataRepository: CartDataRepository,
    @IoDispatcher private val ioDispatchers: CoroutineDispatcher
) : ProductsRepository {

    override suspend fun getAllProducts(
        sort: String?,
        limit: Int
    ): List<ProductEntity> {
        return repository
            .getAllProducts(limit = limit, sort = sort)
            .map { productMapper.toProductEntity(it) }
    }

    override suspend fun getProductsByCategory(
        category: String?,
        sort: String?,
        limit: Int
    ): List<ProductEntity> {
        return repository
            .getProductsByCategory(category = category, sort = sort, limit = limit)
            .map { productMapper.toProductEntity(it) }
    }

    override suspend fun getProductById(productId: Int): ProductEntity {
        val response = repository.getProduct(productId)
        return productMapper.toProductEntity(response)
    }

    override suspend fun addToCart(productId: Int) = withContext(ioDispatchers) {
        cartDataRepository.addToCart(productId)
    }
}