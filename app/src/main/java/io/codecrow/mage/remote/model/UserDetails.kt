package io.codecrow.mage.remote.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserDetails(
    @SerializedName("avatar")
    @Expose
    var avatar: String,

    @SerializedName("displayName")
    @Expose
    var displayName: String,

    @SerializedName("username")
    @Expose
    var username: String,
)
