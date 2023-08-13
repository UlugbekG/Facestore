package cd.ghost.data

import android.content.res.Resources.NotFoundException
import cd.ghost.common.IoDispatcher
import cd.ghost.data.sources.carts.entity.CartItemDataEntity
import cd.ghost.data.sources.carts.entity.ProductDataEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultCartDataRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CartDataRepository {

    private val _cart = MutableStateFlow<List<CartItemDataEntity>>(emptyList())
    override fun getCart(): StateFlow<List<CartItemDataEntity>> = _cart

    override suspend fun newCartItem(product: ProductDataEntity) =
        withContext(ioDispatcher) {
            val list = ArrayList(_cart.value)
            val newItem = CartItemDataEntity(product.id, 1, product)
            list.add(newItem)
            _cart.value = list
        }

    override suspend fun getCartItemById(cartId: Int): CartItemDataEntity =
        withContext(ioDispatcher) {
            val list = ArrayList(_cart.value)
            val cart = list.find { it.productId == cartId }
            return@withContext cart ?: throw NotFoundException()
        }

    override suspend fun changeQuantity(productId: Int, quantity: Int) =
        withContext(ioDispatcher) {
            val list = ArrayList(_cart.value)
            val index = list.indexOfFirst { it.productId == productId }
            val cartItem = list[index] ?: throw NotFoundException()
            list[index] = CartItemDataEntity(
                productId = productId,
                quantity = quantity,
                product = ProductDataEntity(
                    id = cartItem.product.id,
                    title = cartItem.product.title,
                    description = cartItem.product.description,
                    price = cartItem.product.price,
                    category = cartItem.product.category,
                    imageUrl = cartItem.product.imageUrl,
                    rating = cartItem.product.rating,
                    count = cartItem.product.count
                ),
            )
            _cart.value = list
        }

    override suspend fun clear() {
        _cart.value = emptyList()
    }

    override suspend fun delete(productId: Int) =
        withContext(ioDispatcher) {
            val list = ArrayList(_cart.value)
            list.removeAll { it.productId == productId }
            _cart.value = list
        }
}