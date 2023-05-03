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
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.repeatOnLifecycle
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import io.codecrow.mage.R
import io.codecrow.mage.model.Channel
import io.codecrow.mage.ui.components.ChannelViewersItem
import io.codecrow.mage.ui.components.TitleTextStyle
import io.codecrow.mage.ui.components.UserProfileImage
import io.codecrow.mage.ui.theme.*


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
                Toast.makeText(context, it.avatar, Toast.LENGTH_LONG).show()
            }
        )
    } else if (items is BrowseUiState.Loading) {
        LoadingView()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun BrowseScreen(
    items: List<Channel>,
    enterChannel: (_id: String) -> Unit,
    modifier: Modifier = Modifier,
    onClick: (Channel) -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val state = rememberLazyListState()
    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state, positionInLayout = {_,_ -> 0f})}
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    Scaffold(

        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTextStyle()
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = {
//            var nameBrowse by remember { mutableStateOf("Compose") }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it),
                state = state,
                flingBehavior = flingBehavior,

            ) {
                items(items) { it: Channel ->
                    ChannelItem(it)
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
                "VideoTitle",
                "des",
                "",
                listOf(""),
                listOf(""),
                "",
                "User",
                "@DisplayName",
                "",
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
                "VideoTitle",
                "des",
                "",
                listOf(""),
                listOf(""),
                "",
                "User",
                "@DisplayName",
                "",
                "channel"
            )
        )
    MyApplicationTheme {
        BrowseScreen(channels, enterChannel = {})
    }
}

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LoadingChannelItem()
        LoadingChannelItem()
        LoadingChannelItem()
        LoadingChannelItem()
        LoadingChannelItem()
    }
}
