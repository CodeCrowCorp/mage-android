package io.codecrow.mage.ui.channel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.codecrow.mage.data.datasource.ChannelRemote
import io.codecrow.mage.remote.model.Channel
import io.codecrow.mage.remote.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    private val channelRemote: ChannelRemote
) : ViewModel() {

    private val _channelState: MutableStateFlow<Resource<Channel>> =
        MutableStateFlow(Resource.loading())
    val channelState = _channelState.asStateFlow()

    fun getChannelById(channelID : String) {
        channelRemote.getChannelByID(channelID).map {
            _channelState.value = it
        }.launchIn(viewModelScope)
    }

}
