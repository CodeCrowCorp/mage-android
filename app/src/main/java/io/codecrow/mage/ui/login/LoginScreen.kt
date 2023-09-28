package io.codecrow.mage.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Browser
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.codecrow.mage.R
import io.codecrow.mage.utils.Constant


@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Scaffold(modifier = modifier, content = {
        Box(
            modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .size(150.dp)
                        .fillMaxSize()
                )
                DiscordLoginButton("Log in with Discord", R.drawable.ic_discord) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Constant.DiscordUrl))
                    val bundle = Bundle()
                    bundle.putString("User-Agent", "Mage-Mobile")
                    bundle.putString("x-api-key", "cdma2tw7ieznk3nqjpc7l7rkgcxrzm")
                    browserIntent.putExtra(Browser.EXTRA_HEADERS, bundle)
                    context.startActivity(browserIntent)
                }

                GmailLoginButton(name = "Log in with Gmail", icon = R.drawable.ic_gmail) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Constant.GoogleUrl))
                    val bundle = Bundle()
                    bundle.putString("User-Agent", "Mage-Mobile")
                    bundle.putString("x-api-key", "cdma2tw7ieznk3nqjpc7l7rkgcxrzm")
                    browserIntent.putExtra(Browser.EXTRA_HEADERS, bundle)
                    context.startActivity(browserIntent)
                }

                GithubLoginButton(name = "Log in with Github", icon = R.drawable.ic_github) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Constant.GithubUrl))
                    val bundle = Bundle()
                    bundle.putString("User-Agent", "Mage-Mobile")
                    bundle.putString("x-api-key", "cdma2tw7ieznk3nqjpc7l7rkgcxrzm")
                    browserIntent.putExtra(Browser.EXTRA_HEADERS, bundle)
                    context.startActivity(browserIntent)
                }

            }
        }
    })
}

@Composable
fun DiscordLoginButton(name: String, icon: Int, callback: () -> Unit) {
    Button(
        onClick = callback,
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.background
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = name,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun GmailLoginButton(name: String, icon: Int, callback: () -> Unit) {
    Button(
        onClick = callback,
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.onPrimary)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = name,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun GithubLoginButton(name: String, icon: Int, callback: () -> Unit) {
    Button(
        onClick = callback,
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color(Color.Black.toArgb())),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null, colorFilter = ColorFilter.tint(Color.White)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = name,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}