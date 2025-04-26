package com.codeblin.myapplication.ui.screens.todoDetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codeblin.archcore.navigation.NavigationMode
import com.codeblin.archcore.presentation.ArchCoreScreen
import com.codeblin.myapplication.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailsScreen(viewModel: TodoDetailsViewModel) {
    AppTheme {
        ArchCoreScreen(
            topBarTitle = "Home",
            navigationMode = NavigationMode.NAVIGATABLE,
            onTopBarAction = { viewModel.processIntent(TodoDetailsUiIntent.Navigate.Pop) },
            applyPadding = { paddingValues -> Modifier.padding(top = paddingValues.calculateTopPadding()) }
        ) {
            Text("all good")
        }
    }
}
