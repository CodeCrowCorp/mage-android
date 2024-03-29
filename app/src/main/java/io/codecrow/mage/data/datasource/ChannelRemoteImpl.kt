package io.codecrow.mage.data.datasource

import io.codecrow.mage.data.DataException
import io.codecrow.mage.data.Either
import io.codecrow.mage.data.service.ChannelApi
import io.codecrow.mage.data.handle
import io.codecrow.mage.model.Channel
import javax.inject.Inject

class ChannelRemoteImpl @Inject constructor(
    private val api: ChannelApi
): ChannelRemote {

    override suspend fun getChannels(searchQuery: String, skip: Int, limit: Int): Either<DataException, List<Channel>> {
        return handle({
            api.getChannels(searchQuery, skip, limit)
        }) {
            Either.Right(it ?: emptyList())
        }
    }
}