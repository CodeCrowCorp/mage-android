package io.codecrow.mage.data.datasource

import io.codecrow.mage.data.DataException
import io.codecrow.mage.data.Either
import io.codecrow.mage.model.Channel

interface ChannelRemote {

    suspend fun getChannels(searchQuery: String, skip: Int, limit: Int): Either<DataException, List<Channel>>
}