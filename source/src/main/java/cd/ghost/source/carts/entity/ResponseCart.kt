package cd.ghost.source.carts.entity

import com.google.gson.annotations.SerializedName

data class ResponseCart(
    @SerializedName("id") val id: Int?,
    @SerializedName("userId") val userId: Int?,
    @SerializedName("date") val date: String?,
    @SerializedName("products") val products: List<CartItemSourceEntity>?,
    @SerializedName("__v") val v: Int?
)