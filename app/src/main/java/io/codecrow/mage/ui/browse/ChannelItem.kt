package io.codecrow.mage.ui.browse

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.codecrow.mage.remote.model.Channel
import io.codecrow.mage.ui.components.ChannelViewersItem
import io.codecrow.mage.ui.components.UserProfileImage

@Composable
fun ChannelItem(channel: Channel) {
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
                        ChannelViewersItem()
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
                                text = channel.title,
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
                                UserProfileImage(channel.avatar)
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
                                        text = "@"+channel.createdByUsername,
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