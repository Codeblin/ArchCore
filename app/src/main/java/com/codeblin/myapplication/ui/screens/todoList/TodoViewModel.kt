package com.codeblin.myapplication.ui.screens.todoList

import androidx.lifecycle.viewModelScope
import com.codeblin.archcore.viewmodel.ArchCoreViewModel
import com.codeblin.archcore.viewmodel.UiEvent
import com.codeblin.archcore.viewmodel.UiIntent
import com.codeblin.archcore.viewmodel.UiState
import com.codeblin.myapplication.data.TodoUseCase
import kotlinx.coroutines.launch

data class TodoUiState(val todos: List<TodoItem> = emptyList()) : UiState

sealed class TodoUiEvent : UiEvent {
    data class ShowSnackbar(val message: String) : TodoUiEvent()
}

sealed class TodoUiIntent : UiIntent {
    data object GetTodos : TodoUiIntent()
    data class AddTodo(val text: String) : TodoUiIntent()
    data class ToggleTodo(val id: Int) : TodoUiIntent()
    data class DeleteTodo(val id: Int) : TodoUiIntent()
}


class TodoViewModel(
    private val todoUseCase: TodoUseCase,
) : ArchCoreViewModel<TodoUiState, TodoUiEvent, TodoUiIntent>(TodoUiState()) {

    private var nextId = 1

    override fun processIntent(intent: TodoUiIntent) {
        when (intent) {
            is TodoUiIntent.GetTodos -> getTodos()
            is TodoUiIntent.AddTodo -> addTodo(intent.text)
            is TodoUiIntent.ToggleTodo -> toggleTodo(intent.id)
            is TodoUiIntent.DeleteTodo -> deleteTodo(intent.id)
        }
    }

    private fun getTodos() {
        viewModelScope.launch {
            val todos = todoUseCase.execute()
            updateState(uiState.value.copy(todos = todos))
        }
    }

    private fun addTodo(text: String) {
        viewModelScope.launch {
            val newTodo = TodoItem(id = nextId++, text = text)
            todoUseCase.addTodo(newTodo)
            updateState(uiState.value.copy(todos = uiState.value.todos + newTodo))
            sendEvent(TodoUiEvent.ShowSnackbar("Task Added"))
        }
    }

    private fun toggleTodo(id: Int) {
        val updatedTodos = uiState.value.todos.map {
            if (it.id == id) it.copy(isCompleted = !it.isCompleted) else it
        }
        updateState(uiState.value.copy(todos = updatedTodos))
    }

    private fun deleteTodo(id: Int) {
        viewModelScope.launch {
            todoUseCase.deleteTodo(id)
            updateState(uiState.value.copy(todos = uiState.value.todos.filter { it.id != id }))
            sendEvent(TodoUiEvent.ShowSnackbar("Task Deleted"))
        }
    }
}
