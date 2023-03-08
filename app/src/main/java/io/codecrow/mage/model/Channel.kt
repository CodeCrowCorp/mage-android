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

    @SerializedName("isPrivate")
    @Expose
    var isPrivate: Boolean,

    @SerializedName("category")
    @Expose
    var category: List<String>,

    @SerializedName("tags")
    @Expose
    var tags: List<String>,

    @SerializedName("createdById")
    @Expose
    var createdById: String,

    @SerializedName("createdByDisplayName")
    @Expose
    var createdByDisplayName: String,

    @SerializedName("createdByUsername")
    @Expose
    var createdByUsername: String,

    @SerializedName("avatar")
    @Expose
    var avatar: String,

    @SerializedName("isHostActive")
    @Expose
    var isHostActive: Boolean,

    @SerializedName("channelType")
    @Expose
    var channelType: String,
)
