package com.juanzurita.presentation.ads_detail.ui.theme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

val Typography = Typography(
    headlineLarge = TextStyle(
        fontSize = 20.sp,
        color = Color.Unspecified // Se tomará de onSurface automáticamente
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        color = Color.Unspecified
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        color = Color.Unspecified
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        color = Color.Unspecified
    )
)