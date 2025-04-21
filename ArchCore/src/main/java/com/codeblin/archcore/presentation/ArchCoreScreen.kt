package com.codeblin.archcore.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchCoreScreen(
    isLoading: Boolean = false,
    errorMessage: String? = null,
    topBarTitle: String? = null,
    onTopBarAction: (() -> Unit)? = null,  // Action for top bar button if needed
    content: @Composable () -> Unit,
) {
    // Scaffold provides a basic layout structure
    Scaffold(
        topBar = {
            topBarTitle?.let {
                TopAppBar(
                    title = { Text(text = it) },
                    actions = {
                        if (onTopBarAction != null) {
                            IconButton(onClick = { onTopBarAction() }) {
                                Icon(
                                    Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (isLoading) {
                // Show loading indicator if isLoading is true
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
                )
            } else if (errorMessage != null) {
                // Show error message if an error occurs
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                // Show the content of the screen when there are no loading or error states
                content()
            }
        }
    }
}
