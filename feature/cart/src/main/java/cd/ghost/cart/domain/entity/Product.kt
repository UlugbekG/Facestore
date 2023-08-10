package cd.ghost.cart.domain.entity

data class Product(
    val id: Int,
    val title: String?,
    val description: String?,
    val price: String?,
    val category: String?,
    val imageUrl: String?,
    val rating: String?,
    val count: String?
)