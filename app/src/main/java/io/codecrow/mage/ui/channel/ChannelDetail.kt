package io.codecrow.mage.ui.channel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.codecrow.mage.remote.model.Channel
import io.codecrow.mage.ui.components.ChannelViewersItem

@Composable
fun ChannelDetail(channel: Channel, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp) //TODO: set min height
            .padding(10.dp)
            .clip(RoundedCornerShape(4.dp))
//            .clickable { onClick(it) }
    ) {
        Card(
            elevation = CardDefaults.cardElevation(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
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
                        ChannelViewersItem(channel)
                    }
                    Row(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {}
                    Row(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        repeat(5) {
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 25.dp,
                                        bottom = 25.dp
                                    )
                            ) {
                                Text(
                                    text = "@" + channel.createdByUsername,
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