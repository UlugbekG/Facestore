package cd.ghost.fakestore.features.cart.repos

import cd.ghost.cart.domain.entity.CartItem
import cd.ghost.cart.domain.repos.CartRepository
import cd.ghost.common.Container
import cd.ghost.data.CartDataRepository
import cd.ghost.fakestore.features.cart.mapper.CartItemMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultCartRepository @Inject constructor(
    private val cartDataRepository: CartDataRepository,
    private val cartItemMapper: CartItemMapper
) : CartRepository {

    override fun getCart(): Flow<Container<List<CartItem>>> {
        return cartDataRepository.getCart().map { list ->
            try {
                val value = list.map { cartItemMapper.mapToCartItem(it) }
                Container.Success(value)
            } catch (e: Exception) {
                Container.Error(e)
            }
        }
    }

    override suspend fun changeQuantity(cartId: Int, newQuantity: Int) {
        cartDataRepository.changeQuantity(cartId, newQuantity)
    }

    override suspend fun deleteCartItems(cartItemIds: List<Int>) {
        cartItemIds.forEach { cartDataRepository.delete(it) }
    }

    override suspend fun getCartItemById(cartId: Int): CartItem {
        val item = cartDataRepository.getCartItemById(cartId)
        return cartItemMapper.mapToCartItem(item)
    }
}