package io.codecrow.mage.ui.browse

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.codecrow.mage.ui.theme.MyApplicationTheme


@Composable
fun LoadingChannelItem() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

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
                    Spacer(modifier = Modifier.padding(bottom = 10.dp))

                    Row(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(32.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .fillMaxWidth(fraction = 0.24f)
                                .background(brush)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Spacer(
                            modifier = Modifier
                                .height(32.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .fillMaxWidth(fraction = 0.34f)
                                .background(brush)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box{
                        Spacer(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(brush)
                        ) //{
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Play button",
                                modifier = Modifier.size(48.dp),
                                tint = Color.White.copy(alpha = 0.2f),
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
                            Spacer(modifier = Modifier.padding(5.dp))

                            Spacer(
                                modifier = Modifier
                                    .height(22.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .fillMaxWidth(fraction = 1.0f)
                                    .background(brush)
                            )


                            Row(
                                verticalAlignment = Alignment.Bottom,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Spacer(
                                    modifier = Modifier
                                        .size(64.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(brush)
                                )
                                Spacer(modifier = Modifier.padding(start = 10.dp))

                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 25.dp,
                                            bottom = 25.dp
                                        )
                                )
                                {
                                    Spacer(
                                        modifier = Modifier
                                            .height(17.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .fillMaxWidth(fraction = 1.0f)
                                            .background(brush)
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

@Preview(showBackground = true)
@Composable
private fun PortraitPreview() {
    MyApplicationTheme {
        LoadingChannelItem()
    }
}
@Preview(showBackground = true)
@Composable
private fun LandscapePreview() {
    MyApplicationTheme {
        LoadingChannelItem()
    }
}