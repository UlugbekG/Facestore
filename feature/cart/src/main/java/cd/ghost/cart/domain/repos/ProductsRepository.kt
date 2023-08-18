package cd.ghost.cart.domain.repos


interface ProductsRepository {

    /**
     * @throws NotFoundException
     */
    suspend fun getAvailableQuantity(productId: Int): Int?

}