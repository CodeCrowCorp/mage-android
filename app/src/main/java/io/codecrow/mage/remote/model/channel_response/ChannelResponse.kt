package io.codecrow.mage.remote.model.channel_response

import com.google.gson.annotations.SerializedName


data class ChannelResponseItem(

	@field:SerializedName("mods")
	val mods: List<Any?>? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("isAiChatEnabled")
	val isAiChatEnabled: Boolean? = null,

	@field:SerializedName("memberCount")
	val memberCount: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("channelType")
	val channelType: String? = null,

	@field:SerializedName("bans")
	val bans: List<Any?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("userDetails")
	val userDetails: UserDetails? = null,

	@field:SerializedName("tags")
	val tags: List<String?>? = null,

	@field:SerializedName("createdAt")
	val createdAt: Long? = null,

	@field:SerializedName("isLive")
	val isLive: Boolean? = null,

	@field:SerializedName("guests")
	val guests: List<Any?>? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: List<String?>? = null,

	@field:SerializedName("user")
	val user: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: Long? = null,

	@field:SerializedName("planDetails")
	val planDetails: PlanDetails? = null
)


data class PlanDetails(

	@field:SerializedName("lastPaymentDate")
	val lastPaymentDate: Long? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("planTier")
	val planTier: Int? = null,

	@field:SerializedName("user")
	val user: String? = null,

	@field:SerializedName("createdt")
	val createdt: Long? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: Long? = null
)

data class UserDetails(

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)