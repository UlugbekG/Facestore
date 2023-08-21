package cd.ghost.fakestore.features.cart.repos

import cd.ghost.cart.domain.repos.ProductsRepository
import cd.ghost.data.repositories.ProductsDataRepository
import javax.inject.Inject

class AdapterProductsRepository @Inject constructor(
    private val productsDataRepository: ProductsDataRepository,
) : ProductsRepository {

    override suspend fun getAvailableQuantity(productId: Int): Int? {
        return productsDataRepository.getProduct(productId).rating?.count?.toInt()
    }
}