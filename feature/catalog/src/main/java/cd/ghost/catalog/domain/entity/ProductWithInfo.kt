package cd.ghost.catalog.domain.entity

data class ProductWithInfo(
    val product: ProductEntity,
    val isInCart: Boolean,
    val favorite: Boolean
)