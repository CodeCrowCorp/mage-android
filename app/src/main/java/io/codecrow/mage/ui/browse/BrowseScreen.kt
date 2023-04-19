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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import io.codecrow.mage.R
import io.codecrow.mage.model.Channel
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
                    .padding(it)
            ) {
                items(items) { it: Channel ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp) //TODO: set min height
                            .padding(10.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .clickable { onClick(it) }
                    ) {
                        Card(
                            elevation = CardDefaults.cardElevation(),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Box {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    //TODO: add video thumbnail here
                                }
                                Column(
                                    modifier = Modifier.padding(
                                        start = 10.dp,
                                        end = 10.dp,
                                        bottom = 10.dp
                                    )
                                ) {
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
                                            onClick = {}
                                        )

                                        Spacer(modifier = Modifier.width(5.dp))
                                        AssistChip(
                                            label = { Text(text = "158") },
                                            leadingIcon = {
                                                Image(
                                                    painter = painterResource(id = R.drawable.ic_viewers_24),
                                                    contentDescription = "Viewer"
                                                )
                                            },
                                            colors = AssistChipDefaults.assistChipColors(
                                                containerColor = Color.Black.copy(alpha = 0.4f),
                                                labelColor = Color.White,
                                                leadingIconContentColor = Color.White
                                            ),
                                            border = null,
                                            onClick = {}
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .weight(1F)
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .background(
                                                    Color.Black.copy(alpha = 0.5f),
                                                    shape = CircleShape
                                                )
                                                .size(48.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.PlayArrow,
                                                contentDescription = "Play button",
                                                modifier = Modifier.size(48.dp),
                                                tint = Color.LightGray
                                            )
                                        }
                                    }
                                    Row(
                                        modifier = Modifier
                                            .weight(1F)
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.Top,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Column(modifier = Modifier.fillMaxSize()) {
                                            Text(
                                                text = it.title,
                                                color = MaterialTheme.colorScheme.onPrimary,
                                                style = TextStyle(
                                                    // fontFamily = FontFamily(Font(R.font.montserrat)),
                                                    fontSize = 22.sp,
                                                    fontWeight = FontWeight.W600,
                                                    lineHeight = 22.sp,
                                                    letterSpacing = 0.sp,
                                                    textAlign = TextAlign.Start
                                                )
                                            )

                                            Row(
                                                verticalAlignment = Alignment.Bottom,
                                                modifier = Modifier.fillMaxSize()
                                            ) {
                                                UserProfileImage(it.avatar)
                                                Spacer(modifier = Modifier.width(5.dp))
                                                Column(
                                                    Modifier
                                                        .fillMaxWidth()
                                                        .padding(
                                                            top = 25.dp,
                                                            bottom = 25.dp
                                                        )
                                                ) {
                                                    Text(
                                                        text = it.createdByUsername,
                                                        color = MaterialTheme.colorScheme.onPrimary,
                                                        style = TextStyle(
                                                            //  fontFamily = FontFamily("Montserrat"),
                                                            fontSize = 17.sp,
                                                            fontWeight = FontWeight.W500,
                                                            lineHeight = 22.sp,
                                                            letterSpacing = 0.sp,
                                                            textAlign = TextAlign.Left
                                                        )
                                                    )
                                                }
                                            }
                                        }
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
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(color = Color.Blue)
    }
}
