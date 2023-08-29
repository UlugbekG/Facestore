package cd.ghost.catalog.domain

import cd.ghost.catalog.domain.entity.FilterData
import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.repositories.ProductsRepository
import cd.ghost.common.Container
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductsRepository,
) {

    operator fun invoke(filter: FilterData): Flow<Container<List<ProductEntity>>> =
        flow {
            val result = repository.getAllProducts(
                sort = filter.sort.value,
                limit = filter.itemsSize
            )
            emit(result)
        }

}