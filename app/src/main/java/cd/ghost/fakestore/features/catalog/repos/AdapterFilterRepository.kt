package cd.ghost.fakestore.features.catalog.repos

import cd.ghost.catalog.domain.repositories.FilterRepository
import cd.ghost.data.repositories.ProductsDataRepository
import javax.inject.Inject

class AdapterFilterRepository @Inject constructor(
    private val productsDataRepository: ProductsDataRepository
) : FilterRepository {

    override suspend fun getCategories(): List<String> {
        return productsDataRepository.getCategories()
    }

}