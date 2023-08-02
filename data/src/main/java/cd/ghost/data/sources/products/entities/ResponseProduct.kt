package cd.ghost.data.sources.products.entities

import com.google.gson.annotations.SerializedName

data class ResponseProduct(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("price") val price: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("image") val imageUrl: String?,
    @SerializedName("rating") val rating: ProductRating?,
)