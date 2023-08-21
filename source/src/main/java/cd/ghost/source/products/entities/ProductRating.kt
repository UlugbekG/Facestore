package cd.ghost.source.products.entities

import com.google.gson.annotations.SerializedName

data class ProductRating(
    @SerializedName("rate") val rate: String?,
    @SerializedName("count") val count: String?,
)