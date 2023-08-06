package cd.ghost.fakestore.features.catalog.repos

import cd.ghost.catalog.domain.entity.EntityProduct
import cd.ghost.catalog.domain.entity.ProductRating
import cd.ghost.catalog.domain.repos.ProductsRepository
import cd.ghost.data.DataProductsRepository
import javax.inject.Inject

class DefaultCatalogsRepository @Inject constructor(
    private val repository: DataProductsRepository
) : ProductsRepository {


    override suspend fun getAllProducts(
        sort: String?,
        limit: Int
    ): List<EntityProduct> {
        return repository
            .getAllProducts(limit = limit, sort = sort)
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

    override suspend fun getProductsByCategory(
        category: String?,
        sort: String?,
        limit: Int
    ): List<EntityProduct> {
        return repository
            .getProductsByCategory(category = category, sort = sort, limit = limit)
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