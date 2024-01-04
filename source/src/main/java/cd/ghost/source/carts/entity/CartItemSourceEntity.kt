package cd.ghost.source.carts.entity

import com.google.gson.annotations.SerializedName

data class CartItemSourceEntity(
    val id: Int,
    @SerializedName("productId") val productId: Int?,
    @SerializedName("quantity") val quantity: Int?,
    val title: String,
    val description: String,
    val thump: String,
    val price: String
)