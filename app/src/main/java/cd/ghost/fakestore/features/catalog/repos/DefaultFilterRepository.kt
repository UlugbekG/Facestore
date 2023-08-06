package cd.ghost.fakestore.features.catalog.repos

import cd.ghost.catalog.domain.repos.FilterRepository
import cd.ghost.data.DataProductsRepository
import javax.inject.Inject

class DefaultFilterRepository @Inject constructor(
    private val dataProductsRepository: DataProductsRepository
) : FilterRepository {
    override suspend fun getCategories(): List<String> {
        return dataProductsRepository.getCategories()
    }
}