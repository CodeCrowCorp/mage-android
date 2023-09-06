package io.codecrow.mage.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class PlanDetails(
    @SerializedName("_id") @Expose val _id: String,
    @SerializedName("createdAt") @Expose val createdAt: Long,
    @SerializedName("planTier") @Expose val planTier: Int,
    @SerializedName("updatedAt") @Expose val updatedAt: Long,
    @SerializedName("user") @Expose val user: String
)