package cd.ghost.fakestore.features.catalog.mapper

import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.entity.ProductRating
import cd.ghost.source.carts.entity.ProductSourceEntity
import cd.ghost.source.products.entities.ResponseProduct
import javax.inject.Inject

class CatalogProductMapper @Inject constructor() {

    fun toProductEntity(product: ResponseProduct): ProductEntity {
        return ProductEntity(
            id = product.id!!,
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

    fun toProductDataEntity(product: ProductEntity): ProductSourceEntity {
        return ProductSourceEntity(
            id = product.id,
            title = product.title,
            description = product.description,
            price = product.price,
            category = product.category,
            imageUrl = product.imageUrl,
            rating = product.rating?.rate,
            count = product.rating?.count
        )
    }

}