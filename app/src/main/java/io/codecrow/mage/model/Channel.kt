package io.codecrow.mage.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Channel(
    @SerializedName("_id")
    @Expose
    var _id: String,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("description")
    @Expose
    var description: String,

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String,

    @SerializedName("category")
    @Expose
    var category: List<String>,

    @SerializedName("tags")
    @Expose
    var tags: List<String>,

    @SerializedName("user")
    @Expose
    var user: String,

    @SerializedName("createdByDisplayName")
    @Expose
    var createdByDisplayName: String,

    @SerializedName("createdByUsername")
    @Expose
    var createdByUsername: String,

    @SerializedName("avatar")
    @Expose
    var avatar: String,

    @SerializedName("channelType")
    @Expose
    var channelType: String,
)
