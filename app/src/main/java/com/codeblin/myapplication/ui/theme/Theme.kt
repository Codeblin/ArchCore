package com.codeblin.myapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.codeblin.archcore.theme.ArchTheme

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when {
        useDarkTheme -> MyAppColorScheme.dark
        else -> MyAppColorScheme.light
    }

    ArchTheme(
        darkTheme = useDarkTheme,
        content = {
            MaterialTheme( // Optional: if you want to fully override MaterialTheme
                colorScheme = colors,
                typography = MaterialTheme.typography, // or your own
                content = content
            )
        }
    )
}