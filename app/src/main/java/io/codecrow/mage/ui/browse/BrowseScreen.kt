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
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.Lifecycle
import io.codecrow.mage.model.Channel
import kotlinx.coroutines.flow.collect

@Composable
fun BrowseScreen(modifier: Modifier = Modifier, viewModel: BrowseViewModel = hiltViewModel()) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val context = LocalContext.current
    val items by produceState<BrowseUiState>(
        initialValue = BrowseUiState.Loading,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = STARTED) {
            viewModel.uiState.collect {
                value = it
            }

        }
    }
    if (items is BrowseUiState.Success) {
        BrowseScreen(
            items = (items as BrowseUiState.Success).data,
            enterChannel = viewModel::enterChannel,
            modifier = modifier,
            onClick = {
                Toast.makeText(context, it.title, Toast.LENGTH_LONG).show()
            }
        )
    }
}

@Composable
internal fun BrowseScreen(
    items: List<Channel>,
    enterChannel: (_id: String) -> Unit,
    modifier: Modifier = Modifier,
    onClick: (Channel) -> Unit = {}
) {
    Column(modifier) {
        var nameBrowse by remember { mutableStateOf("Compose") }
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
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFf5f5f5))) {
            itemsIndexed(items) {i: Int, it: Channel ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(126.dp)
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(color = Color(0xFFf5f5f5))
                        .clickable { onClick(it) }
                ) {

                    Column {
                        Text(
                            text = it.title,
                            textAlign = TextAlign.Start,
                            color = Color(0xAA000000),
                            modifier = Modifier.padding(all = 24.dp),
                            fontSize = dpToSp(dp = 18.dp)
                        )

                        Text(
                            text = it.description,
                            textAlign = TextAlign.Start,
                            color = Color(0xAA000000),
                            modifier = Modifier.padding(horizontal = 24.dp),
                            fontSize = dpToSp(dp = 14.dp)
                        )

                        Divider(
                            color = Color(0xAA000000),
                            modifier = modifier.height(1.dp)
                        )
                    }


                }
            }
        }
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

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }
