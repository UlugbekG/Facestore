package cd.ghost.catalog.domain.entity

data class ProductWithCartInfo(
    val product: ProductEntity,
    val isInCart: Boolean
)