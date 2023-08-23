package cd.ghost.catalog.domain

import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.repositories.FavoritesRepository
import cd.ghost.common.Container
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {

    operator fun invoke(): Flow<List<ProductEntity>> {
        return favoritesRepository.getProducts()
    }


}