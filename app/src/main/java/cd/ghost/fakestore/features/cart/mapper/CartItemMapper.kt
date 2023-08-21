package cd.ghost.fakestore.features.cart.mapper

import cd.ghost.cart.domain.entity.CartItem
import cd.ghost.cart.domain.entity.Product
import cd.ghost.data.repositories.ProductsDataRepository
import cd.ghost.source.carts.entity.CartItemDataEntity
import javax.inject.Inject

class CartItemMapper @Inject constructor(
    private val productsDataRepository: ProductsDataRepository,
) {

    suspend fun toCartItem(item: CartItemDataEntity): CartItem {
        val productDataEntity = productsDataRepository.getProduct(item.productId!!)
        return CartItem(
            id = item.productId!!,
            product = Product(
                id = productDataEntity.id!!,
                title = productDataEntity.title,
                description = productDataEntity.description,
                price = productDataEntity.price,
                category = productDataEntity.category,
                imageUrl = productDataEntity.imageUrl,
                rating = productDataEntity.rating?.rate,
                count = productDataEntity.rating?.count
            ),
            quantity = item.quantity!!,
        )
    }

}