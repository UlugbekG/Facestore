package cd.ghost.fakestore.features.favorites.mapper

import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.entity.ProductRating
import cd.ghost.data.database.favorites.entity.ProductDataEntity
import javax.inject.Inject

class ProductsMapper @Inject constructor() {

    fun toProductEntity(product: ProductDataEntity): ProductEntity {
        return ProductEntity(
            id = product.id,
            title = product.title,
            description = product.description,
            price = product.price,
            category = product.category,
            imageUrl = product.imageUrl,
            rating = ProductRating(
                rate = product.rate,
                count = product.count
            )
        )
    }

    fun toProductDataEntity(product: ProductEntity): ProductDataEntity {
        return ProductDataEntity(
            id = product.id,
            title = product.title,
            description = product.description,
            price = product.price,
            category = product.category,
            imageUrl = product.imageUrl,
            rate = product.rating?.rate,
            count = product.rating?.count
        )
    }
}