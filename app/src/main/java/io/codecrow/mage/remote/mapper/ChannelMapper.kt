package io.codecrow.mage.remote.mapper

import android.util.Log
import com.google.gson.GsonBuilder
import io.codecrow.mage.remote.model.Channel
import io.codecrow.mage.remote.model.channel_response.ChannelResponseItem
import io.codecrow.mage.remote.utils.Mapper
import javax.inject.Inject

class ChannelMapper @Inject
constructor() :
    Mapper<ChannelResponseItem, Channel> {

    override suspend fun map(input: ChannelResponseItem): Channel {
        return Channel(
            id = input.id.orEmpty(),
            title = input.title.orEmpty(),
            avatar = input.userDetails?.avatar.orEmpty(),
            createdByUsername = input.userDetails?.username.orEmpty(),
            memberCount = input.memberCount.toString()
        )
    }

    suspend fun mapAllChannels(botItems: ArrayList<ChannelResponseItem>): ArrayList<Channel> {
        val allBots = ArrayList<Channel>()
        Log.i("__TAG", GsonBuilder().setPrettyPrinting().create().toJson(botItems))
        botItems.forEachIndexed { index, channelResponseItem ->
            allBots.add(map(channelResponseItem))
        }
        return allBots
    }
}