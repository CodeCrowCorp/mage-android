package io.codecrow.mage.data.datasource

import io.codecrow.mage.data.service.ChannelApi
import io.codecrow.mage.remote.mapper.ChannelMapper
import io.codecrow.mage.remote.model.Channel
import io.codecrow.mage.remote.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChannelRemoteImpl @Inject constructor(
    private val channelApi: ChannelApi,
    private val channelMapper: ChannelMapper
): ChannelRemote {

    override fun getAllChannels(
        searchQuery: String,
        skip: Int,
        limit: Int
    ): Flow<Resource<ArrayList<Channel>>> {
        return flow {
            emit(Resource.loading())
            emit(Resource.success(data = channelMapper.mapAllChannels(channelApi.getChannels(searchQuery, skip, limit))))
        }.catch { e ->
            emit(Resource.error(e))
        }
    }

    override fun getChannelByID(channelId: String): Flow<Resource<Channel>> {
        return flow {
            emit(Resource.loading())
            emit(Resource.success(data = channelMapper.map(channelApi.getChannel(channelId))))
        }.catch { e ->
            emit(Resource.error(e))
        }
    }

}