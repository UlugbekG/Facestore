package cd.ghost.catalog.domain

import cd.ghost.catalog.domain.entity.EntityProduct
import cd.ghost.common.Container
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsListUseCase @Inject constructor(
    private val repository: ProductsRepository
) {

    operator fun invoke(category: String, sort: String): Flow<Container<List<EntityProduct>>> =
        flow {
            val list = repository.getProducts(category = category, sort = sort)
            emit(Container.Success(list))
        }
            .catch {
            
        }

}