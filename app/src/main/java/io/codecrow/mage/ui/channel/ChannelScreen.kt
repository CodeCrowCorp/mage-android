package io.codecrow.mage.ui.channel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.codecrow.mage.remote.model.Channel
import io.codecrow.mage.remote.utils.DataStatus
import io.codecrow.mage.ui.browse.LoadingView
import io.codecrow.mage.ui.theme.MyApplicationTheme

@Composable
fun ChannelScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ChannelViewModel = hiltViewModel(),
    channelID : String
) {
    val channelDetail by viewModel.channelState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getChannelById(channelID)
    }

    when (channelDetail.status) {
        DataStatus.LOADING -> {
            LoadingView()
        }
        DataStatus.SUCCESS -> {
            channelDetail.data.let {
                it?.let { it1 ->
                    ChannelScreen(
                        items = it1,
                        modifier = modifier,
                        onClick = {
                            navController.navigate("channel/$it")
                        }
                    )
                }
            }
        }
        else -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun ChannelScreen(
    items: Channel,
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
    val channel = Channel(
            "",
            "VideoTitle",
            "des",
            "",
            ""
        )
    MyApplicationTheme {
        ChannelScreen(channel)
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun LandscapePreview() {
    val channel = Channel(
        "",
        "VideoTitle",
        "des",
        "",
        ""
    )
    MyApplicationTheme {
        ChannelScreen(channel)
    }
}
