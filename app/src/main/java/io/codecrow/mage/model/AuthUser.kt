package io.codecrow.mage.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@kotlinx.serialization.Serializable
data class AuthUser(
    @SerializedName("freshJwt") @Expose val freshJwt: String,
    @SerializedName("user") @Expose val user: User
)