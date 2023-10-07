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
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import io.codecrow.mage.data.datasource.ChannelRemote
import io.codecrow.mage.model.Channel
import io.codecrow.mage.utils.Constant.X_API_KEY_NEW
import io.codecrow.mage.utils.PreferenceHelper
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(private val preferenceHelper: PreferenceHelper,
                                          private val channelRemote: ChannelRemote) : ViewModel() {

    private val _uiState = MutableStateFlow<BrowseUiState>(BrowseUiState.Loading)
    val uiState: StateFlow<BrowseUiState> = _uiState

    private val _subscribeResponse = MutableStateFlow<BrowseUiState>(BrowseUiState.Loading)
    val subscribeResponse: StateFlow<BrowseUiState> = _subscribeResponse

    init {
        getChannels("", 0, 100)
        subscribeToChannel()
    }

    private fun getChannels(searchQuery: String, skip: Int, limit: Int) {
        viewModelScope.launch {
            channelRemote.getChannels(searchQuery, skip, limit).either({
                _uiState.value = BrowseUiState.Error(it)
            }, {
                Log.d("HERE", it.toString())
                _uiState.value = BrowseUiState.Success(it)
            })
        }

    }

    private fun subscribeToChannel() {
        Firebase.messaging.token.addOnCompleteListener {
            viewModelScope.launch {
                val jsonObject = JsonObject()
                jsonObject.addProperty("deviceToken",it.result)
                val hashMap = hashMapOf("x-api-key" to X_API_KEY_NEW,"User-Agent" to "Mage-Mobile", "token" to (preferenceHelper.getToken()?:""),"userId" to (preferenceHelper.getUserId() ?: ""))
                channelRemote.subscribeToChannel(hashMap,jsonObject).either({
                    _subscribeResponse.value = BrowseUiState.Error(it)
                }, {
                    Log.d("HERE", it.toString())
                    _subscribeResponse.value = BrowseUiState.SuccessSubscribe(it)
                })
            }
        }
    }
}

sealed interface BrowseUiState {
    object Loading : BrowseUiState
    data class Error(val throwable: Throwable) : BrowseUiState
    data class Success(val data: List<Channel>) : BrowseUiState

    data class SuccessSubscribe(val data: JsonObject) : BrowseUiState
}
