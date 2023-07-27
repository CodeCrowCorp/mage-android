package io.codecrow.mage.ui.channel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import io.codecrow.mage.model.Channel
import io.codecrow.mage.model.UserDetails
import io.codecrow.mage.ui.browse.LoadingView
import io.codecrow.mage.ui.theme.MyApplicationTheme

@Composable
fun ChannelScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ChannelViewModel = hiltViewModel()
) {
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
    if (items is ChannelUiState.Success) {
        ChannelScreen(
            items = (items as ChannelUiState.Success).data,
            modifier = modifier,
            onClick = {
                navController.navigate("channel/$it")
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
    Text(
        text = "Yo",
        color = Color.Red,
        style = TextStyle(
            // fontFamily = FontFamily(Font(R.font.montserrat)),
            fontSize = 22.sp,
            fontWeight = FontWeight.W600,
            lineHeight = 22.sp,
            letterSpacing = 0.sp,
            textAlign = TextAlign.Start
        )
    )
}

// Previews

@Preview(showBackground = true)
@Composable
private fun PortraitPreview() {
    val channels =
        listOf(
            Channel(
                "",
                "VideoTitle",
                "des",
                "",
                listOf(""),
                listOf(""),
                "",
                0,
                "channel",
                UserDetails("","","")
            )
        )
    MyApplicationTheme {
        ChannelScreen(channels)
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun LandscapePreview() {
    val channels =
        listOf(
            Channel(
                "",
                "VideoTitle",
                "des",
                "",
                listOf(""),
                listOf(""),
                "",
                0,
                "channel",
                UserDetails("","","")
            )
        )
    MyApplicationTheme {
        ChannelScreen(channels)
    }
}
