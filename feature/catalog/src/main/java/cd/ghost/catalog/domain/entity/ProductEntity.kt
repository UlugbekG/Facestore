package cd.ghost.catalog.domain.entity

data class ProductEntity(
    val id: Int,
    val title: String?,
    val price: String?,
    val description: String?,
    val category: String?,
    val imageUrl: String?,
    val rating: ProductRating?,
)