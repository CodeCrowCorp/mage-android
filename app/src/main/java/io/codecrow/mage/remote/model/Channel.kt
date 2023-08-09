package io.codecrow.mage.remote.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Channel(
    var title: String,
    var createdByUsername: String,
    var avatar: String,
)
