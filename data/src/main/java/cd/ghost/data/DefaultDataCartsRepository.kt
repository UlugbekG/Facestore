package cd.ghost.data

import cd.ghost.data.sources.carts.CartsApi
import cd.ghost.data.sources.carts.entity.CartProduct
import cd.ghost.data.sources.carts.entity.ResponseCart
import javax.inject.Inject

class DefaultDataCartsRepository @Inject constructor(
    private val cartsApi: CartsApi
) : DataCartsRepository {
    override suspend fun getAllCarts(
        limit: Int,
        sort: String,
        startdate: String?,
        enddate: String?
    ): List<ResponseCart> {
        TODO("Not yet implemented")
    }

    override suspend fun getCartsByUserID(userID: Int): List<CartProduct> {
        TODO("Not yet implemented")
    }

    override suspend fun getCart(cartId: Int): ResponseCart {
        TODO("Not yet implemented")
    }
}