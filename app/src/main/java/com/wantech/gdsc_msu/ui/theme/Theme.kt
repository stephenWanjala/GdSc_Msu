package com.wantech.gdsc_msu.ui.theme

import android.app.Activity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorPalette = darkColors(
    primary = PrimaryDark,
    primaryVariant = TertiaryDark,
    secondary = SecondaryDark,
    surface = SurfaceDark,
    background = SurfaceDark,

    )

private val LightColorPalette = lightColors(
    primary = PrimaryLight,
    primaryVariant = TertiaryLight,
    secondary = SecondaryLight,
    background = SurfaceLight,
    surface = SurfaceLight,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun GdSc_MsuTheme(
    theme: Int,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val autoColors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette


    val colors = when (theme) {
        Theme.LIGHT_THEME.themeValue -> LightColorPalette
        Theme.NIGHT_THEME.themeValue -> DarkColorPalette
        else -> autoColors
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor =
                colors.surface.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}


enum class Theme(val themeValue: Int) {
    FOLLOW_SYSTEM(themeValue = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    LIGHT_THEME(themeValue = AppCompatDelegate.MODE_NIGHT_NO),
    NIGHT_THEME(themeValue = AppCompatDelegate.MODE_NIGHT_YES);
}