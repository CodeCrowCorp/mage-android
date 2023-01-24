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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.repeatOnLifecycle
import io.codecrow.mage.ui.theme.MyApplicationTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import io.codecrow.mage.model.Channel

@Composable
fun BrowseScreen(modifier: Modifier = Modifier, viewModel: BrowseViewModel = hiltViewModel()) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val items by produceState<BrowseUiState>(
        initialValue = BrowseUiState.Loading,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = STARTED) {
            viewModel.uiState.collect { value = it }
        }
    }
    if (items is BrowseUiState.Success) {
        BrowseScreen(
            items = (items as BrowseUiState.Success).data,
            enterChannel = viewModel::enterChannel,
            modifier = modifier
        )
    }
}

@Composable
internal fun BrowseScreen(
    items: List<Channel>,
    enterChannel: (_id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
//        var nameBrowse by remember { mutableStateOf("Compose") }
//        Row(
//            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            TextField(
//                value = nameBrowse,
//                onValueChange = { nameBrowse = it }
//            )
//
//            Button(modifier = Modifier.width(96.dp), onClick = { enterChannel(nameBrowse) }) {
//                Text("Save")
//            }
//        }
        items.forEach {
            Text("Channel Title: ${it.title}")
        }
    }
}

// Previews

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    var channels = listOf(Channel("","","","",false,listOf(""),listOf(""),"","",true, "channel"))
    MyApplicationTheme {
        BrowseScreen(channels, enterChannel = {})
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitPreview() {
    var channels = listOf(Channel("","","","",false,listOf(""),listOf(""),"","",true, "channel"))
    MyApplicationTheme {
        BrowseScreen(channels, enterChannel = {})
    }
}
