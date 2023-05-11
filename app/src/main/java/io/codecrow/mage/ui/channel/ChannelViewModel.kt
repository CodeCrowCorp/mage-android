package io.codecrow.mage.ui.channel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.codecrow.mage.data.datasource.ChannelRemote
import io.codecrow.mage.model.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor(
    private val channelRemote: ChannelRemote
) : ViewModel() {

    private val _uiState = MutableStateFlow<ChannelUiState>(ChannelUiState.Loading)
    val uiState: StateFlow<ChannelUiState> = _uiState


    init {
        getChannels("", 0, 100)
    }

    private fun getChannels(searchQuery: String, skip: Int, limit: Int) {
        viewModelScope.launch {
            channelRemote.getChannels(searchQuery, skip, limit).either({
                _uiState.value = ChannelUiState.Error(it)
            }, {
                Log.d("HERE", it.toString())
                _uiState.value = ChannelUiState.Success(it)
            })
        }

    }
}

sealed interface ChannelUiState {
    object Loading : ChannelUiState
    data class Error(val throwable: Throwable) : ChannelUiState
    data class Success(val data: List<Channel>) : ChannelUiState
}
