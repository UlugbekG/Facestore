package cd.ghost.data

import cd.ghost.data.sources.carts.entity.CartProduct
import cd.ghost.data.sources.carts.entity.ResponseCart

interface DataCartsRepository {

    suspend fun getAllCarts(
        limit: Int,
        sort: String,
        startdate: String?,
        enddate: String?
    ): List<ResponseCart>

    suspend fun getCartsByUserID(userID: Int): List<CartProduct>

    suspend fun getCart(cartId: Int): ResponseCart

}