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

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import io.codecrow.mage.model.Channel
import io.codecrow.mage.ui.theme.*
import kotlinx.coroutines.selects.select


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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BrowseScreen(
    items: List<Channel>,
    enterChannel: (_id: String) -> Unit,
    modifier: Modifier = Modifier,
    onClick: (Channel) -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { CenterAlignedTopAppBar(title = {Text("Live Channels")}, scrollBehavior = scrollBehavior) },
        content = {
//            var nameBrowse by remember { mutableStateOf("Compose") }
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(it)) {
                items(items) { it: Channel ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp) //TODO: set min height
                            .padding(10.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .clickable { onClick(it) }
                    ) {
                        Box {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            ) {
                                //TODO: add video thumbnail here
                            }
                            Column (modifier = Modifier.padding(10.dp)) {
                                Row(
                                    modifier = Modifier
                                        .weight(1F)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.Top,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    AssistChip(
                                        label = { Text(text = "LIVE") },
                                        colors = AssistChipDefaults.assistChipColors(
                                            containerColor = Color.Red,
                                            labelColor = Color.White,
                                        ),
                                        border = null,
                                        onClick = {},
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    AssistChip(
                                        label = { Text(text = "158") },
                                        leadingIcon = {
                                            Icon(
                                                Icons.Filled.Info,
                                                contentDescription = "Viewers",
                                            )
                                        },
                                        colors = AssistChipDefaults.assistChipColors(
                                            containerColor = Color.Black.copy(alpha = 0.4f),
                                            labelColor = Color.White,
                                            leadingIconContentColor = Color.White
                                        ),
                                        border = null,
                                        onClick = {},
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .weight(1F)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = "Play button"
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .weight(1F)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    Column {
                                        //TODO: add image. set src from it.avatar
                                        //TODO: add it.createdByUsername
                                        Text(
                                            text = it.title,
                                            textAlign = TextAlign.Start,
                                            color = Color.Black,
                                            fontSize = dpToSp(dp = 18.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
    })
}

// Previews

@Preview(showBackground = true)
@Composable
private fun PortraitPreview() {
    var channels =
        listOf(
            Channel(
                "",
                "title",
                "des",
                "",
                false,
                listOf(""),
                listOf(""),
                "",
                "",
                "",
                "",
                true,
                "channel"
            )
        )
    MyApplicationTheme {
        BrowseScreen(channels, enterChannel = {})
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun LandscapePreview() {
    var channels =
        listOf(
            Channel(
                "",
                "title",
                "des",
                "",
                false,
                listOf(""),
                listOf(""),
                "",
                "",
                "",
                "",
                true,
                "channel"
            )
        )
    MyApplicationTheme {
        BrowseScreen(channels, enterChannel = {})
    }
}

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }
