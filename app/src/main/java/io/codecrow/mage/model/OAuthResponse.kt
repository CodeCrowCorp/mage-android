package io.codecrow.mage.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@kotlinx.serialization.Serializable
data class OAuthResponse(
    @SerializedName("message") @Expose val message: String,
    @SerializedName("loginUrl") @Expose val loginUrl: String
)