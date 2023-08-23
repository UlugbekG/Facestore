package cd.ghost.catalog.domain

import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.repositories.FavoritesRepository
import javax.inject.Inject

class ManageFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {

    suspend fun save(product: ProductEntity) {
        favoritesRepository.save(product)
    }

    suspend fun remove(product: ProductEntity) {
        favoritesRepository.remove(product.id)
    }

}