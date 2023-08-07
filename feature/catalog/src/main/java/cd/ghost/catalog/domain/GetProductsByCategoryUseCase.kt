package cd.ghost.catalog.domain

import cd.ghost.catalog.domain.entity.EntityProduct
import cd.ghost.catalog.domain.entity.FilterData
import cd.ghost.catalog.domain.repos.ProductsRepository
import cd.ghost.common.Container
import cd.ghost.common.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetProductsByCategoryUseCase @Inject constructor(
    private val repository: ProductsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    operator fun invoke(
        filter: FilterData
    ): Flow<Container<List<EntityProduct>>> = flow {
        try {
            val list = repository.getProductsByCategory(
                sort = filter.sort.value,
                limit = filter.itemsSize,
                category = filter.category.value
            )
            emit(Container.Success(list))
        } catch (e: Exception) {
            emit(Container.Error(e))
        }
    }.flowOn(ioDispatcher)
}