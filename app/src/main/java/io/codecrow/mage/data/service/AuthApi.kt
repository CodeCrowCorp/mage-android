package io.codecrow.mage.data.service

import io.codecrow.mage.model.AuthUser
import io.codecrow.mage.model.Channel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface AuthApi {
    @GET("auth/me")
    suspend fun getCurrentUser(@HeaderMap headerMap : HashMap<String,String>): Response<AuthUser>
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