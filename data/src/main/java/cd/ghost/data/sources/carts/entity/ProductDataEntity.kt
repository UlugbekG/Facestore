package cd.ghost.data.sources.carts.entity

data class ProductDataEntity(
    val id: Int,
    val title: String?,
    val description: String?,
    val price: String?,
    val category: String?,
    val imageUrl: String?,
    val rating: String?,
    val count: String?
)