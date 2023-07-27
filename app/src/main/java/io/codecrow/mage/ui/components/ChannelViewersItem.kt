package io.codecrow.mage.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.codecrow.mage.R
import io.codecrow.mage.model.Channel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelViewersItem(channel: Channel) {
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
        label = { Text(text = channel.memberCount.toString()) },
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