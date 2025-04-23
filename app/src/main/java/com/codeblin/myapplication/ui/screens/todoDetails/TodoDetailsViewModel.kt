package com.codeblin.myapplication.ui.screens.todoDetails

import com.codeblin.archcore.viewmodel.ArchCoreViewModel
import com.codeblin.archcore.viewmodel.UiEvent
import com.codeblin.archcore.viewmodel.UiIntent
import com.codeblin.archcore.viewmodel.UiState

data class TodoDetailsUiState(
    val title: String,
) : UiState

sealed class TodoDetailsUiEvent : UiEvent {
}

sealed class TodoDetailsUiIntent : UiIntent {
    sealed class Navigate : TodoDetailsUiIntent() {
        data object Pop : Navigate()
    }
}

class TodoDetailsViewModel :
    ArchCoreViewModel<TodoDetailsUiState, TodoDetailsUiEvent, TodoDetailsUiIntent>(
        TodoDetailsUiState(
            title = ""
        )
    ) {
    override fun processIntent(intent: TodoDetailsUiIntent) {
        when (intent) {
            is TodoDetailsUiIntent.Navigate.Pop -> navigateBack()
        }
    }
}