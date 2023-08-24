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

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.codecrow.mage.ui.theme.MyApplicationTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var isSplashScreen = mutableStateOf(true)

        lifecycleScope.launch(Dispatchers.Default) {
            isSplashScreen.value = false
        }

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                isSplashScreen.value
            }
        }

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val intent = result.data
                    handleAuthResult(intent?.data)
                }
            }

        setContent {
            MyApplicationTheme {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MainNavigation(resultLauncher)
                    }
                }
            }
        }
    }

    private fun handleAuthResult(data: Uri?) {
        if (data != null) {
            val urlString = data.toString()
            if (urlString.contains("oauth_verifier")) {
                val oauth_verifier = data.getQueryParameter("oauth_verifier")
            } else {
                val accessToken = data.getQueryParameter("access_token")
                val userID = data.getQueryParameter("user_id")
                val scope = data.getQueryParameter("scope")
                val tokenType = data.getQueryParameter("token_type")
                val expiresIn = data.getQueryParameter("expires_in")
            }
            Log.e("TAG", "handleAuthResult: " + data)
        }
    }

}

//
//composable("channel/{channelId}") { backStackEntry ->
//    val channelId = backStackEntry.arguments?.getString("channelId")
//    ChannelScreen(channelId)
//}

