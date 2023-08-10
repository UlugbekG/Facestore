package cd.ghost.fakestore.features.catalog.mapper

import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.entity.ProductRating
import cd.ghost.data.sources.products.entities.ResponseProduct
import javax.inject.Inject

class ProductMapper @Inject constructor() {

    fun toProductEntity(product: ResponseProduct): ProductEntity {
        return ProductEntity(
            id = product.id,
            title = product.title,
            description = product.description,
            price = product.price,
            category = product.category,
            imageUrl = product.imageUrl,
            rating = ProductRating(
                rate = product.rating?.rate,
                count = product.rating?.count
            )
        )
    }

}