package cd.ghost.fakestore.catalog

import cd.ghost.catalog.domain.ProductsRepository
import cd.ghost.catalog.domain.entity.EntityProduct
import cd.ghost.catalog.domain.entity.ProductRating
import cd.ghost.data.DataProductsRepository
import javax.inject.Inject

class CatalogProductsRepository @Inject constructor(
    private val repository: DataProductsRepository
) : ProductsRepository {

    override suspend fun getProducts(
        category: String?,
        sort: String?
    ): List<EntityProduct> {
        return repository
            .getAllProducts(category, limit = 5, sort)
            .map {
                EntityProduct(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    category = it.category,
                    imageUrl = it.imageUrl,
                    rating = ProductRating(
                        rate = it.rating?.rate,
                        count = it.rating?.count
                    )
                )
            }
    }
}