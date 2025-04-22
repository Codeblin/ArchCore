package com.codeblin.archcore.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object ArchColorScheme {
    val light = lightColorScheme(
        primary = Color(0xFF0066CC),
        onPrimary = Color.White,
        background = Color(0xFFF5F5F5),
        onBackground = Color.Black
    )

    val dark = darkColorScheme(
        primary = Color(0xFF3399FF),
        onPrimary = Color.Black,
        background = Color(0xFF121212),
        onBackground = Color.White
    )
}