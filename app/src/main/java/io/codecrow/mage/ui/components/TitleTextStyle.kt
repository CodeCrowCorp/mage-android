package io.codecrow.mage.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun TitleTextStyle(){
    Text(
        text = "Live Channels",
        color = Color.Black,
        style = androidx.compose.ui.text.TextStyle(
            //fontFamily = FontFamily("Montserrat"),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 22.sp,
            letterSpacing = 0.sp,
            textAlign = TextAlign.Left
        )
    )
}