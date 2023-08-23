package cd.ghost.data.repositories

import android.content.res.Resources.NotFoundException
import cd.ghost.common.Container
import cd.ghost.common.flow.LazyFlowSubjectFactory
import cd.ghost.source.carts.CartDataSource
import cd.ghost.source.carts.entity.CartItemDataEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefCartDataRepository @Inject constructor(
    private val cartDataSource: CartDataSource,
    lazyFlowSubjectFactory: LazyFlowSubjectFactory
) : CartDataRepository {

    private val cartSubject = lazyFlowSubjectFactory.create {
        cartDataSource.getCart()
    }

    override fun getCart(): Flow<Container<List<CartItemDataEntity>>> {
        return cartSubject.listen()
    }

    override suspend fun addToCart(productId: Int, quantity: Int) {
        cartDataSource.saveToCart(productId, quantity)
        notifyChanges()
    }

    override suspend fun getCartItemById(id: Int): CartItemDataEntity {
        return cartDataSource.getCart().firstOrNull { it.id == id } ?: throw NotFoundException()
    }

    override suspend fun deleteCartItem(ids: List<Int>) {
        ids.forEach { cartDataSource.delete(it) }
        cartSubject.newAsyncLoad(silently = true)
    }

    override suspend fun deleteAll() {
        cartDataSource.deleteAll()
        cartSubject.newAsyncLoad(silently = true)
    }

    override suspend fun changeQuantity(cartId: Int, quantity: Int) {
        val cartItem = getCartItemById(cartId)
        val productId = cartItem.productId
        cartDataSource.saveToCart(productId ?: 0, quantity)
        cartSubject.newAsyncLoad(silently = true)
    }

    override fun reload() {
        cartSubject.newAsyncLoad()
    }

    private suspend fun notifyChanges() {
        cartSubject.updateWith(Container.Success(cartDataSource.getCart()))
    }
}