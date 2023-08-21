package cd.ghost.source.carts

import cd.ghost.source.carts.entity.CartItemDataEntity
import cd.ghost.source.carts.entity.ResponseCart
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CartsApi {

    /**
     * Get all carts.
     * @param limit
     * @param sort The default value is in ascending mode, you can use it with 'desc' or 'asc' as you want.
     * @param startdate If you don't add any start date it will fetch from the beginning of time.
     * @param enddate  If you don't add any end date it will fetch until now.
     */
    @GET("/carts")
    suspend fun getAllCarts(
        @Query("limit") limit: Int = 10,
        @Query("sort") sort: String = "desc",
        @Query("startdate") startdate: String?,
        @Query("enddate") enddate: String?,
    ): List<ResponseCart>

    /**
     * Get a single cart.
     * @param cartId
     */
    @GET("/carts/{id}")
    suspend fun getCart(
        @Path("id") cartId: Int
    ): ResponseCart

    /**
     * Get user carts.
     * We can also use date range as query string to get your ideal results
     * @param userId
     */
    @GET("/carts/user/{id}")
    suspend fun getUserCart(
        @Path("id") userId: Int
    ): List<CartItemDataEntity>

}