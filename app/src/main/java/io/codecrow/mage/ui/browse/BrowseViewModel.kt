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
import io.codecrow.mage.data.datasource.ChannelRemote
import io.codecrow.mage.remote.model.Channel
import io.codecrow.mage.remote.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(
    private val channelRemote: ChannelRemote
) : ViewModel() {

    private val _channelListState: MutableStateFlow<Resource<ArrayList<Channel>>> =
        MutableStateFlow(Resource.loading())
    val channelListState = _channelListState.asStateFlow()

    init {
        getChannels("", 0, 100)
    }

    fun enterChannel(_id: String) {
        viewModelScope.launch {
//            channelRepository.add(name)
        }
    }

    private fun getChannels(searchQuery: String, skip: Int, limit: Int) {
        channelRemote.getAllChannels(searchQuery, skip, limit).map {
            _channelListState.value = it
        }.launchIn(viewModelScope)
    }
}

