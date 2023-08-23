package cd.ghost.catalog.domain

import cd.ghost.catalog.domain.repositories.FilterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: FilterRepository,
) {

    operator fun invoke(): Flow<List<String>> = flow {
        try {
            val categories = repository.getCategories()
            emit(categories)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}