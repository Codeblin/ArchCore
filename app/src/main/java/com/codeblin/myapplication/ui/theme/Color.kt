package com.codeblin.myapplication.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object MyAppColorScheme {
    val light = lightColorScheme(
        primary = Color(0xFF00A86B),       // Green
        onPrimary = Color.White,
        background = Color(0xFFFFFFFF),
        onBackground = Color.Black,
        error = Color(0xFFC24545)
    )

    val dark = darkColorScheme(
        primary = Color(0xFF66D19E),
        onPrimary = Color.Black,
        background = Color(0xFF3A3838),
        onBackground = Color.White,
        error = Color(0xFFC24545)
    )
}