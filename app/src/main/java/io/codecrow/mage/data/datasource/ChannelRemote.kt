package io.codecrow.mage.data.datasource

import io.codecrow.mage.remote.model.Channel
import io.codecrow.mage.remote.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ChannelRemote {
    fun getAllChannels(searchQuery: String, skip: Int, limit: Int): Flow<Resource<ArrayList<Channel>>>

    fun getChannelByID(channelId: String) : Flow<Resource<Channel>>
}