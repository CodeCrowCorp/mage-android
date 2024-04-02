package io.codecrow.mage.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.codecrow.mage.R
import io.codecrow.mage.ui.theme.shapes

@Composable
fun UserProfileImage(avatar: String?) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(avatar)
            .crossfade(true)
            .build(),
        error = painterResource(R.drawable.ic_logo),
//    placeholder = painterResource(R.drawable.placeholder),
        contentDescription = "test",//stringResource(R.string.description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(64.dp)
            .clip(RoundedCornerShape(16.dp))
//            .border(
//                2.dp,
//                brush = Brush.linearGradient(
//                    colors = listOf(
//                        Color.Magenta,
//                        Color.Blue
//                    ),
//                    start = Offset(50f, 40f),
//                    end = Offset(100f, -10f)
//                ),
//                shapes.medium //RoundedCornerShape(16.dp)
//            )
    )
}
