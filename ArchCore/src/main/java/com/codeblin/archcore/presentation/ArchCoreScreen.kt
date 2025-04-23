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
import com.codeblin.archcore.navigation.NavigationMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchCoreScreen(
    isLoading: Boolean = false,
    errorMessage: String? = null,
    topBarTitle: String? = null,
    navigationMode: NavigationMode = NavigationMode.NONE,
    floatingActionButton: @Composable () -> Unit = {},
    onTopBarAction: (() -> Unit)? = null,
    topBar: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Scaffold(
        floatingActionButton = floatingActionButton,
        topBar = {
            when {
                topBar != null -> topBar()
                topBarTitle != null -> {
                    TopAppBar(
                        title = { Text(text = topBarTitle) },
                        navigationIcon = {
                            if (navigationMode == NavigationMode.NAVIGATABLE && onTopBarAction != null) {
                                IconButton(onClick = onTopBarAction) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        },
                        actions = {
                            // You can add trailing icons or other actions here if needed
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }

                errorMessage != null -> {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> content()
            }
        }
    }
}


