package cd.ghost.fakestore.features.catalog.repos

import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.repositories.ProductsRepository
import cd.ghost.data.repositories.ProductsDataRepository
import cd.ghost.fakestore.features.catalog.mapper.CatalogProductMapper
import javax.inject.Inject

class AdapterProductsRepository @Inject constructor(
    private val repository: ProductsDataRepository,
    private val catalogProductMapper: CatalogProductMapper,
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
}