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

package io.codecrow.mage.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.codecrow.mage.data.datasource.AuthRepo
import io.codecrow.mage.model.AuthUser
import io.codecrow.mage.utils.Constant.X_API_KEY
import io.codecrow.mage.utils.PreferenceHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.RuntimeException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val preferenceHelper: PreferenceHelper,private val authRepo: AuthRepo) : ViewModel() {

    private val _userResultMutable  = MutableLiveData<CommonUiState<AuthUser>>()
    val userResult : LiveData<CommonUiState<AuthUser>> = _userResultMutable


    fun isUserLoggedIn():Boolean {
        return preferenceHelper.getUserId()?.isNotEmpty() == true && preferenceHelper.getToken()?.isNotEmpty() == true
    }

    fun saveTokenAndUserId(token: String,userId: String) {
        preferenceHelper.setToken(token)
        preferenceHelper.setUserId(userId)
    }

    fun getCurrentUser() {
        viewModelScope.launch(Dispatchers.IO){
            val hashMap = hashMapOf("x-api-key" to X_API_KEY,"User-Agent" to "Mage-Mobile", "token" to (preferenceHelper.getToken()?:""),"userId" to (preferenceHelper.getUserId() ?: ""))
            authRepo.getCurrentUser(hashMap).either({
                _userResultMutable.postValue(CommonUiState.Error(it))
            },{
                if (it != null) {
                    preferenceHelper.saveAuthUser(it)
                    _userResultMutable.postValue(CommonUiState.Success(it))
                } else {
                    _userResultMutable.postValue(CommonUiState.Error(RuntimeException("Unauthorized User")))
                }
            })
        }
    }
}

sealed class CommonUiState<out T: Any> {
    object Loading : CommonUiState<Nothing>()
    data class Error(val throwable: Throwable) : CommonUiState<Nothing>()
    data class Success<out T: Any>(val data: T) : CommonUiState<T>()
}