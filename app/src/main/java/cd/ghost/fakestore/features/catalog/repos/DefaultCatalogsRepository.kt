package cd.ghost.fakestore.features.catalog.repos

import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.repos.ProductsRepository
import cd.ghost.data.CartDataRepository
import cd.ghost.data.ProductsDataRepository
import cd.ghost.fakestore.features.catalog.mapper.CatalogProductMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultCatalogsRepository @Inject constructor(
    private val repository: ProductsDataRepository,
    private val catalogProductMapper: CatalogProductMapper,
    private val cartDataRepository: CartDataRepository,
) : ProductsRepository {

    override suspend fun getAllProducts(
        sort: String?,
        limit: Int
    ): List<ProductEntity> {
        return repository
            .getAllProducts(limit = limit, sort = sort)
            .map { catalogProductMapper.toProductEntity(it) }
    }

    override suspend fun getProductsByCategory(
        category: String?,
        sort: String?,
        limit: Int
    ): List<ProductEntity> {
        return repository
            .getProductsByCategory(category = category, sort = sort, limit = limit)
            .map { catalogProductMapper.toProductEntity(it) }
    }

    override suspend fun getProductById(productId: Int): ProductEntity {
        val response = repository.getProduct(productId)
        return catalogProductMapper.toProductEntity(response)
    }

    override suspend fun getCartItemIds(): Flow<List<Int?>> {
        return cartDataRepository
            .getCart()
            .map { it.map { item -> item.productId } }
    }
}