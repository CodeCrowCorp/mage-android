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

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import io.codecrow.mage.ui.theme.MyApplicationTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var isSplashScreen = mutableStateOf(true)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                isSplashScreen.value
            }
        }
        mainViewModel.userResult.observe(this@MainActivity) {
            isSplashScreen.value = false
            if (it is CommonUiState.Success) {
                setContent {
                    loadContent("main")
                }
            } else if (it is CommonUiState.Error) {

            } else {

            }
        }
        if (mainViewModel.isUserLoggedIn()) {
            mainViewModel.getCurrentUser()
        } else {
            isSplashScreen.value = false
        }

        setContent {
            loadContent("login")
        }
    }
    @Composable
    fun loadContent(route: String) {
        MyApplicationTheme {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation(route)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.data?.let {
            //http://localhost:5173/?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXlsb2FkIjoiNjRmODI1MjkzYjE5NmM0NjMzODdkMzE2IiwiZXhwIjoxNjkzOTkxMjQ4LCJpYXQiOjE2OTM5ODQwNDh9.ENZ2rOHcZDdOYsnuhZpI0FPqkHHXKq1Ymv-Nbmgp1-E&userId=64f825293b196c463387d316
            //TODO: handle url
            Log.d("Url",""+it.toString())
            val token = it.getQueryParameter("token")
            val userID = it.getQueryParameter("userId")
            mainViewModel.saveTokenAndUserId(token!!,userID!!)
            mainViewModel.getCurrentUser()
        }
    }
}

//
//composable("channel/{channelId}") { backStackEntry ->
//    val channelId = backStackEntry.arguments?.getString("channelId")
//    ChannelScreen(channelId)
//}

