package cd.ghost.data.sources.carts.entity

import com.google.gson.annotations.SerializedName

data class ResponseCart(
    @SerializedName("id") val id: Int?,
    @SerializedName("userId") val userId: Int?,
    @SerializedName("date") val date: String?,
    @SerializedName("products") val products: List<CartItemDataEntity>?,
    @SerializedName("__v") val v: Int?
)