package com.juanzurita.presentation.ads_detail.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColorLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryColorLightContainer,
    onPrimaryContainer = OnPrimaryLight,

    secondary = SecondaryColorLight,
    onSecondary = OnSecondaryLight,
    secondaryContainer = SecondaryColorLight,
    onSecondaryContainer = OnSecondaryLight,

    surface = SurfaceColorLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceColorLight,
    onSurfaceVariant = OnSurfaceVariantLight,

    background = SurfaceContainerLight
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColorDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryColorDarkContainer,
    onPrimaryContainer = OnPrimaryDark,
    secondary = SecondaryColorDark,
    onSecondary = OnSecondaryDark,
    secondaryContainer = SecondaryColorDark,
    onSecondaryContainer = OnSecondaryDark,
    surface = SurfaceColorDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceColorDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    background = SurfaceContainerDark
)

@Composable
fun IdealistaTechTestTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
