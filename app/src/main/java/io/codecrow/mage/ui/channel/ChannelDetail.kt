package io.codecrow.mage.ui.channel

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.codecrow.mage.R
import io.codecrow.mage.model.Channel
import io.codecrow.mage.ui.components.ChannelViewersItem
import io.codecrow.mage.ui.components.UserProfileImage

@OptIn(ExperimentalMaterial3Api::class)
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
                        ChannelViewersItem()
                    }
                    Row(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
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
                            Text(
                                text = channel.description,
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = TextStyle(
                                    // fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.W600,
                                    lineHeight = 22.sp,
                                    letterSpacing = 0.sp,
                                    textAlign = TextAlign.Start
                                )
                            )

                            Text(
                                text = "Vectors",
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = TextStyle(
                                    //  fontFamily = FontFamily("Montserrat"),
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.W500,
                                    lineHeight = 15.sp,
                                    letterSpacing = 0.sp,
                                    textAlign = TextAlign.Left
                                )
                            )

                            Text(
                                text = "Tags",
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = TextStyle(
                                    //  fontFamily = FontFamily("Montserrat"),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.W500,
                                    lineHeight = 15.sp,
                                    letterSpacing = 0.sp,
                                    textAlign = TextAlign.Left
                                )
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                UserProfileImage(channel.avatar)
                                Spacer(modifier = Modifier.width(5.dp))
                                Column(
                                    Modifier
                                     //   .fillMaxWidth()
                                        .padding()
                                ) {
                                    Text(
                                        text = "@" + channel.createdByUsername,
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        style = TextStyle(
                                            //  fontFamily = FontFamily("Montserrat"),
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.W500,
                                            lineHeight = 11.sp,
                                            letterSpacing = 0.sp,
                                            textAlign = TextAlign.Left
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
//                                    AssistChip(
//                                        label = { Text(text = "38") },
//                                        leadingIcon = {
//                                            Image(
//                                                painter = painterResource(id = R.drawable.ic_group_24),
//                                                contentDescription = "Viewer"
//                                            )
//                                        },
//                                        colors = AssistChipDefaults.assistChipColors(
//                                            containerColor = Color.Black.copy(alpha = 0.4f),
//                                            labelColor = Color.White,
//                                            leadingIconContentColor = Color.White
//                                        ),
//                                        border = null,
//                                        onClick = {}
//                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_group_24),
                                            contentDescription = "Viewer",
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "38",
                                            style = TextStyle(
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.W500,
                                                lineHeight = 15.sp,
                                                letterSpacing = 0.sp,
                                                textAlign = TextAlign.Left,
                                                color = Color.Black.copy(alpha = 0.5f),
                                            )
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(75.dp))
                                OutlinedButton(
                                    onClick = { /* Handle button click */ },
                                    border = BorderStroke(1.dp, Color(0xFFFF4081)),
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier.padding(8.dp),
                                    contentPadding = PaddingValues(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    )) {
                                    Text(
                                        text = "Unsubscribe",
                                        color = Color(0xFFFF4081),

                                    )
                                }

                            }
                        }
                    }


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
                                    text = "Viewers (95)",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = TextStyle(
                                        //  fontFamily = FontFamily("Montserrat"),
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.W500,
                                        lineHeight = 11.sp,
                                        letterSpacing = 0.sp,
                                        textAlign = TextAlign.Left
                                    )
                                )
                                Spacer(modifier = Modifier.height(2.dp))

                                Text(
                                    text = "Creator" +"@"+ channel.createdByUsername,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = TextStyle(
                                        //  fontFamily = FontFamily("Montserrat"),
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.W500,
                                        lineHeight = 11.sp,
                                        letterSpacing = 0.sp,
                                        textAlign = TextAlign.Left
                                    )
                                )
                                Spacer(modifier = Modifier.height(2.dp))

                                Text(
                                    text = "Mod" +"@"+ channel.createdByUsername,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = TextStyle(
                                        //  fontFamily = FontFamily("Montserrat"),
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.W500,
                                        lineHeight = 11.sp,
                                        letterSpacing = 0.sp,
                                        textAlign = TextAlign.Left
                                    )
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "@" + channel.createdByUsername,
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        style = TextStyle(
                                            //  fontFamily = FontFamily("Montserrat"),
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.W500,
                                            lineHeight = 11.sp,
                                            letterSpacing = 0.sp,
                                            textAlign = TextAlign.Left
                                        )
                                    )
                                    OutlinedButton(
                                        onClick = { /* Handle button click */ },
                                        border = BorderStroke(1.dp, Color.Gray),
                                        shape = RoundedCornerShape(4.dp),
                                        modifier = Modifier
                                            .size(25.dp)
                                            .padding(bottom = 5.dp, end = 2.dp, start = 10.dp)
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Image(
                                                painter = painterResource(R.drawable.ic_more_horiz_24),
                                                contentDescription = "More options",
                                                // modifier = Modifier.size(.dp),
                                                contentScale = ContentScale.Fit,
                                                modifier = Modifier
                                                    .size(20.dp)
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
}