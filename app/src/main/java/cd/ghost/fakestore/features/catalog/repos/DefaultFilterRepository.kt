package cd.ghost.fakestore.features.catalog.repos

import cd.ghost.catalog.domain.repos.FilterRepository
import cd.ghost.data.ProductsDataRepository
import javax.inject.Inject

class DefaultFilterRepository @Inject constructor(
    private val productsDataRepository: ProductsDataRepository
) : FilterRepository {

    override suspend fun getCategories(): List<String> {
        return productsDataRepository.getCategories()
    }

}