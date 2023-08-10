package cd.ghost.fakestore.features.cart.mapper

import cd.ghost.cart.domain.entity.CartItem
import cd.ghost.cart.domain.entity.Product
import cd.ghost.data.ProductsDataRepository
import cd.ghost.data.sources.carts.entity.CartItemDataEntity
import javax.inject.Inject

class CartItemMapper @Inject constructor(
    private val productsDataRepository: ProductsDataRepository
) {

    suspend fun mapToCartItem(item: CartItemDataEntity): CartItem {
        val product = productsDataRepository.getProduct(item.productId!!)
        return CartItem(
            cartId = item.productId!!,
            productId = item.productId,
            quantity = item.quantity!!,
            product = Product(
                id = product.id!!,
                title = product.title,
                description = product.description,
                price = product.price,
                category = product.category,
                imageUrl = product.imageUrl,
                rating = product.rating?.rate,
                count = product.rating?.count
            )
        )
    }

}