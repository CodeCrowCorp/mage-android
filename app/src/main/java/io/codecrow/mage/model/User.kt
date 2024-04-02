package io.codecrow.mage.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@kotlinx.serialization.Serializable
data class User(
    @SerializedName("_id") @Expose val _id: String,
    @SerializedName("avatar") @Expose val avatar: String,
    @SerializedName("createdAt") @Expose val createdAt: String,
    @SerializedName("displayName") @Expose val displayName: String,
    @SerializedName("isOnline") @Expose val isOnline: Boolean,
//    @SerializedName("planDetails") @Expose val planDetails: PlanDetails,
    @SerializedName("providerId") @Expose val providerId: String,
    @SerializedName("providerType") @Expose val providerType: String,
    @SerializedName("username") @Expose val username: String
)
