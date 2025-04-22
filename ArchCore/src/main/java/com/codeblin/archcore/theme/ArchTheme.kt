package com.codeblin.archcore.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalSpacing = staticCompositionLocalOf { AppSpacing() }

@Composable
fun ArchTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> ArchColorScheme.dark
        else -> ArchColorScheme.light
    }

    CompositionLocalProvider(
        LocalSpacing provides AppSpacing()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = ArchTypography.default,
            content = content
        )
    }
}