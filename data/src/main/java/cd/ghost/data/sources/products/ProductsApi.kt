package cd.ghost.data.sources.products

import cd.ghost.data.sources.products.entities.ResponseProduct
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ProductsApi {

    /**
     * Get all products
     * @param limit use limit(Number)
     * @param sort Default value is in ascending mode, you can use with 'desc' or 'asc' as you want.
     * @param category
     */
    @GET("/products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String?,
        @Query("limit") limit: Int? = 10,
        @Query("sort") sort: String? = "desc"
    ): List<ResponseProduct>

    /**
     * Get all products
     * @param limit use limit(Number)
     * @param sort Default value is in ascending mode, you can use with 'desc' or 'asc' as you want.
     */
    @GET("/products")
    suspend fun getAllProducts(
        @Query("limit") limit: Int? = 10,
        @Query("sort") sort: String? = "desc"
    ): List<ResponseProduct>

    /**
     * Get a single product
     * @param productId
     */
    @GET("/products/{id}")
    suspend fun getProduct(@Path("id") productId: Int): ResponseProduct

    /**
     * Get all categories
     *       "electronics",
     *       "jewelery",
     *       "men's clothing",
     *       "women's clothing"
     */
    @GET("/products/categories")
    suspend fun getCategories(): List<String>

}