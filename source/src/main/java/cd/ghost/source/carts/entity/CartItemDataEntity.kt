package cd.ghost.source.carts.entity

import com.google.gson.annotations.SerializedName

data class CartItemDataEntity(
    val id: Int,
    @SerializedName("productId") val productId: Int?,
    @SerializedName("quantity") val quantity: Int?,
)