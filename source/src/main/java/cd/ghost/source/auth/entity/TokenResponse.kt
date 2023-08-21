package cd.ghost.source.auth.entity

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("token") val value: String?
)
