package cd.ghost.data.sources.carts.entity

import com.google.gson.annotations.SerializedName

data class CartItemDataEntity(
    @SerializedName("productId") val productId: Int?,
    @SerializedName("quantity") val quantity: Int?
)