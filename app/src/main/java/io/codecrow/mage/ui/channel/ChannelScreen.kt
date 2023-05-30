package io.codecrow.mage.ui.channel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import io.codecrow.mage.model.Channel
import io.codecrow.mage.ui.browse.ChannelItem
import io.codecrow.mage.ui.browse.LoadingView
import io.codecrow.mage.ui.components.Screen
import io.codecrow.mage.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

@Composable
fun ChannelScreen(modifier: Modifier = Modifier, navController: NavController, viewModel: ChannelViewModel = hiltViewModel()) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val context = LocalContext.current
    val items by produceState<ChannelUiState>(
        initialValue = ChannelUiState.Loading,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.uiState.collect {
                value = it
            }

        }
    }
//    val coroutineScope = rememberCoroutineScope()

    if (items is ChannelUiState.Success) {
        ChannelScreen(
            items = (items as ChannelUiState.Success).data,
            modifier = modifier,
            onClick = {navController.navigate(Screen.ChannelScreen.route)

//            onClick = { channelId ->
//                    coroutineScope.launch {
//                        navController.navigate("channel/$channelId")
            }
        )
    } else if (items is ChannelUiState.Loading) {
        LoadingView()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun ChannelScreen(
    items: List<Channel>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val state = rememberLazyListState()
    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state, positionInLayout = {_,_ -> 0f}) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    Scaffold(

        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it),
                state = state,
                flingBehavior = flingBehavior,

                ) {
                items(items) { channel ->
                    ChannelItem(channel) {
                       // onClick(channel._id)
                        onClick("ChannelScreen/${channel._id}")
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
                "DisplayName",
                "",
                "channel"
            )
        )
    MyApplicationTheme {
        ChannelScreen(channels)
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
                "DisplayName",
                "",
                "channel"
            )
        )
    MyApplicationTheme {
        ChannelScreen(channels)
    }
}
