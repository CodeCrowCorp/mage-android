/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.codecrow.mage.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat


private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    //   primaryContent = LightPrimaryContent,
    secondary = LightSecondary,
    //   secondaryVariant = LightSecondary,
    onPrimary = LightPrimaryContent,
    onSecondary = LightSecondaryContent,
    background = LightBase100,
    onBackground = LightBaseContent,
    surface = LightBase200,
    onSurface = LightBaseContent,
    //   error = MaterialTheme.colors.error,
    //   onError = MaterialTheme.colors.onError,
    //  surfaceColor = LightBase300,
    //  scaffoldBackground = LightNeutral,
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    // primaryVariant = DarkPrimary,
    secondary = DarkSecondary,
    //   secondaryVariant = DarkSecondary,
    onPrimary = DarkPrimaryContent,
    onSecondary = DarkSecondaryContent,
    background = DarkBase100,
    onBackground = DarkBaseContent,
    surface = DarkBase200,
    onSurface = DarkBaseContent,
    //  error = MaterialTheme.colors.error,
    //  onError = MaterialTheme.colors.onError,
    //   surfaceColor = DarkBase300,
    //  scaffoldBackground = DarkNeutral,
)


//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40

/* Other default colors to override
background = Color(0xFFFFFBFE),
surface = Color(0xFFFFFBFE),
onPrimary = Color.White,
onSecondary = Color.White,
onTertiary = Color.White,
onBackground = Color(0xFF1C1B1F),
onSurface = Color(0xFF1C1B1F),
*/
//)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
