package cd.ghost.data.sources.products.entities

import com.google.gson.annotations.SerializedName

data class ProductRating(
    @SerializedName("rate") val rate: String?,
    @SerializedName("count") val count: String?,
)