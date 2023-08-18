package cd.ghost.fakestore.features.cart.repos

import cd.ghost.cart.domain.entity.CartItem
import cd.ghost.cart.domain.repos.CartRepository
import cd.ghost.common.Container
import cd.ghost.data.CartDataRepository
import cd.ghost.fakestore.features.cart.mapper.CartItemMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdapterCartRepository @Inject constructor(
    private val cartDataRepository: CartDataRepository,
    private val cartItemMapper: CartItemMapper,
) : CartRepository {

    override suspend fun changeQuantity(cartItemId: Int, newQuantity: Int) {
        cartDataRepository.changeQuantity(cartItemId, newQuantity)
    }

    override suspend fun deleteCartItems(cartItemIds: List<Int>) {
        cartDataRepository.deleteCartItem(cartItemIds)
    }

    override suspend fun getCartItemById(id: Int): CartItem {
        return cartItemMapper.toCartItem(cartDataRepository.getCartItemById(id))
    }

    override fun getCartItems(): Flow<Container<List<CartItem>>> {
        return cartDataRepository.getCart().map { container ->
            container.suspendMap { list ->
                list.map { cartItemMapper.toCartItem(it) }
            }
        }
    }

    override fun reload() {
        cartDataRepository.reload()
    }

}