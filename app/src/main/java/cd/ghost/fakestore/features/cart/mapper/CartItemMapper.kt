package cd.ghost.fakestore.features.cart.mapper

import cd.ghost.cart.domain.entity.CartItem
import cd.ghost.cart.domain.entity.Product
import cd.ghost.data.ProductsDataRepository
import cd.ghost.data.sources.carts.entity.CartItemDataEntity
import javax.inject.Inject

class CartItemMapper @Inject constructor() {

    fun toCartItem(item: CartItemDataEntity): CartItem {
        return CartItem(
            cartId = item.productId!!,
            productId = item.productId!!,
            quantity = item.quantity!!,
            product = Product(
                id = item.product.id,
                title = item.product.title,
                description = item.product.description,
                price = item.product.price,
                category = item.product.category,
                imageUrl = item.product.imageUrl,
                rating = item.product.rating,
                count = item.product.count
            )
        )
    }

}