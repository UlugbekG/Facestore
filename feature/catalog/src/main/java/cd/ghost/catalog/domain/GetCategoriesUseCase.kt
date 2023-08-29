package cd.ghost.catalog.domain

import cd.ghost.catalog.domain.repositories.FilterRepository
import cd.ghost.common.Container
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: FilterRepository,
) {
    fun getCategories(): Flow<Container<List<String>>> = flow {
        emit(repository.getCategories())
    }
}