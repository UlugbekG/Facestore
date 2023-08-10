package cd.ghost.catalog.domain

import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.repos.ProductsRepository
import cd.ghost.common.Container
import cd.ghost.common.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    operator fun invoke(productId: Int): Flow<Container<ProductEntity>> =
        flow {
            try {
                val product = repository.getProductById(
                    productId = productId
                )
                emit(Container.Success(product))
            } catch (e: Exception) {
                emit(Container.Error(e))
            }
        }.flowOn(ioDispatcher)
}