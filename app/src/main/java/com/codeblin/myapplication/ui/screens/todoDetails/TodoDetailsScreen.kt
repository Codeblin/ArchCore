package com.codeblin.myapplication.ui.screens.todoDetails

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.codeblin.myapplication.ui.theme.AppTheme


@Composable
fun TodoDetailsScreen(viewModel: TodoDetailsViewModel) {

}

@Preview
@Composable
fun ButtonPreview() {
    AppTheme {
        Button(onClick = {}) {
            Text("Test Button")
        }
    }
}