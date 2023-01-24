/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.codecrow.mage.ui.browse

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.codecrow.mage.data.repository.ChannelRepository
import io.codecrow.mage.model.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(
private val channelRepository: ChannelRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<BrowseUiState>(BrowseUiState.Loading)
    val uiState: StateFlow<BrowseUiState> = _uiState


    init {
        getChannels()
    }
    fun enterChannel(_id: String) {
        viewModelScope.launch {
//            channelRepository.add(name)
        }
    }

    private fun getChannels() {
        viewModelScope.launch {
            channelRepository.getChannels().either({
                _uiState.value = BrowseUiState.Error(it)
            }, {
                Log.d("HERE", it.toString())
                _uiState.value = BrowseUiState.Success(it)
            })
        }

    }
}

sealed interface BrowseUiState {
    object Loading : BrowseUiState
    data class Error(val throwable: Throwable) : BrowseUiState
    data class Success(val data: List<Channel>) : BrowseUiState
}
