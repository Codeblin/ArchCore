package com.codeblin.myapplication.ui.screens.todoList

import androidx.lifecycle.viewModelScope
import com.codeblin.archcore.navigation.NavigationUiEvent
import com.codeblin.archcore.viewmodel.ArchCoreViewModel
import com.codeblin.archcore.viewmodel.UiEvent
import com.codeblin.archcore.viewmodel.UiIntent
import com.codeblin.archcore.viewmodel.UiState
import com.codeblin.myapplication.data.TodoRepository
import com.codeblin.myapplication.navigation.AppDestination
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
    sealed class Navigate : TodoUiIntent() {
        data class ToDetails(val id: String) : Navigate()
    }
}


class TodoViewModel(
    private val repository: TodoRepository,
) : ArchCoreViewModel<TodoUiState, TodoUiEvent, TodoUiIntent>(TodoUiState()) {

    private var nextId = 1

    override fun processIntent(intent: TodoUiIntent) {
        when (intent) {
            is TodoUiIntent.GetTodos -> getTodos()
            is TodoUiIntent.AddTodo -> addTodo(intent.text)
            is TodoUiIntent.ToggleTodo -> toggleTodo(intent.id)
            is TodoUiIntent.DeleteTodo -> deleteTodo(intent.id)
            is TodoUiIntent.Navigate.ToDetails -> navigateToDetails(intent.id)
        }
    }

    private fun navigateToDetails(id: String) {
        navigateTo(AppDestination.TodoDetails, "id" to id)
    }

    private fun getTodos() {
        viewModelScope.launch {
            val todos = repository.getData()
            updateState(uiState.value.copy(todos = todos))
        }
    }

    private fun addTodo(text: String) {
        viewModelScope.launch {
            val newTodo = TodoItem(id = nextId++, text = text)
            repository.addTodo(newTodo)
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
            repository.deleteTodo(id)
            updateState(uiState.value.copy(todos = uiState.value.todos.filter { it.id != id }))
            sendEvent(TodoUiEvent.ShowSnackbar("Task Deleted"))
        }
    }
}
