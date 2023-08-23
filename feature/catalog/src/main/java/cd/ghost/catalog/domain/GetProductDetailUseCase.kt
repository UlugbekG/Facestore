package cd.ghost.catalog.domain

import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.entity.ProductWithInfo
import cd.ghost.catalog.domain.repositories.CartRepository
import cd.ghost.catalog.domain.repositories.FavoritesRepository
import cd.ghost.catalog.domain.repositories.ProductsRepository
import cd.ghost.common.Container
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val cartRepository: CartRepository,
    private val favoritesRepository: FavoritesRepository
) {

    fun getProduct(productId: Int): Flow<Container<ProductWithInfo>> {
        return combine(
            cartRepository.getProductIdsInCart(),
            favoritesRepository.getProducts(),
            ::merge
        ).map { container ->
            container.suspendMap { ids ->
                ProductWithInfo(
                    product = productsRepository.getProductById(productId),
                    isInCart = ids.idsInCart.contains(productId),
                    favorite = ids.idsFavorite.contains(productId)
                )
            }
        }
    }

    private fun merge(
        idsInCart: Container<Set<Int?>>,
        favorites: List<ProductEntity>
    ): Container<ProductIds> {
        return idsInCart.map { ids ->
            ProductIds(
                idsInCart = ids,
                idsFavorite = favorites.map { it.id }.toSet()
            )
        }
    }

    fun reload() {
        cartRepository.reload()
    }

    data class ProductIds(
        val idsInCart: Set<Int?>,
        val idsFavorite: Set<Int>
    )
}