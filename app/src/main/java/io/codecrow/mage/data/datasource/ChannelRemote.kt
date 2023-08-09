package io.codecrow.mage.data.datasource

import io.codecrow.mage.data.DataException
import io.codecrow.mage.data.Either
import io.codecrow.mage.remote.model.Channel
import io.codecrow.mage.remote.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ChannelRemote {
    fun getAllChannels(searchQuery: String, skip: Int, limit: Int): Flow<Resource<ArrayList<Channel>>>
}