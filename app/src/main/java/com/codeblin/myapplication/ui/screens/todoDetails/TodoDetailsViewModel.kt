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
}

class TodoDetailsViewModel :
    ArchCoreViewModel<TodoDetailsUiState, TodoDetailsUiEvent, TodoDetailsUiIntent>(
        TodoDetailsUiState(
            title = ""
        )
    ) {
    override fun processIntent(intent: TodoDetailsUiIntent) {

    }
}