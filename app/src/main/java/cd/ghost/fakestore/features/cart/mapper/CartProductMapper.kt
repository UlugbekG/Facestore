package cd.ghost.fakestore.features.cart.mapper

import cd.ghost.cart.domain.entity.Product
import cd.ghost.source.products.entities.ResponseProduct
import javax.inject.Inject

class CartProductMapper @Inject constructor() {

    fun toProduct(product: ResponseProduct): Product {
        return Product(
            id = product.id!!,
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