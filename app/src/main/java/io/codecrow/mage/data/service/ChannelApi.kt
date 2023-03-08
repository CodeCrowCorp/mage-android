package io.codecrow.mage.data.service

import io.codecrow.mage.model.Channel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChannelApi {
    @GET("channel")
    suspend fun createChannel(): List<Channel>

    @GET("channels/update")
    suspend fun updateChannel(): List<Channel>

    @GET("channels")
    suspend fun deleteChannel(@Query("channelId") channelId: String): List<Channel>

    @GET("channel")
    suspend fun getChannel(@Query("channelId") channelId: String): Channel

    @GET("channels/friend")
    suspend fun getFriendChannel(@Query("title") title: String): Channel

    @GET("channels/me/hosted")
    suspend fun getMyChannels(@Query("searchQuery") searchQuery: String, @Query("techStack") techStack: String, @Query("skip") skip: String, @Query("limit") limit: String): List<Channel>

    @GET("channels")
    suspend fun getChannels(@Query("searchQuery") searchQuery: String, @Query("skip") skip: Int, @Query("limit") limit: Int): Response<List<Channel>>

    @GET("channels/user")
    suspend fun getChannelsByUserId(@Query("searchQuery") searchQuery: String, @Query("skip") skip: String, @Query("limit") limit: String): List<Channel>

    @GET("channels/live-streams")
    suspend fun getChannelLiveStreams(@Query("channelId") channelId: String): List<Channel>

    @GET("channels/live-streams/recent")
    suspend fun getChannelRecentLiveStream(@Query("channelId") channelId: String): List<Channel>

    @GET("channels/blocks")
    suspend fun blockUserFromChannel(@Query("channelId") channelId: String): List<Channel>

    @GET("channels/blocks")
    suspend fun unblockUserFromChannel(@Query("channelId") channelId: String): List<Channel>

    @GET("channels/update-properties")
    suspend fun updateChannelProperties(): List<Channel>

    @GET("channels/attachments")
    suspend fun addChannelAttachment(@Query("channelId") channelId: String, @Query("attachmentURL") attachmentURL: String): List<Channel>

    @GET("channels/attachments")
    suspend fun deleteChannelAttachment(@Query("channelId") channelId: String, @Query("attachmentURL") attachmentURL: String): List<Channel>

    @GET("channels/notification-subscribers")
    suspend fun addChannelNotificationSubscriber(@Query("channelId") channelId: String): List<Channel>

    @GET("channels/notification-subscribers")
    suspend fun deleteChannelNotificationSubscriber(@Query("channelId") channelId: String): List<Channel>

    @GET("channels/blocks")
    suspend fun getBlockedUsers(@Query("channelId") channelId: String): List<Channel>

    @GET("channels/blocks/check")
    suspend fun checkIfUserBlocked(@Query("channelId") channelId: String): List<Channel>


}


//createChannel,
//updateChannel,
//deleteChannel,
//getChannel,
//getFriendChannel,
//getMyChannels,
//getChannels,
//getChannelsByUserId,
//getChannelLiveStreams,
//getChannelRecentLiveStream,
//blockUserFromChannel,
//unblockUserFromChannel,
//updateChannelProperties,
//addChannelAttachment,
//deleteChannelAttachment,
//addChannelNotificationSubscriber,
//deleteChannelNotificationSubscriber,
//getBlockedUsers,
//checkIfUserBlocked