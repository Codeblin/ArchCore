package com.codeblin.myapplication.data

import com.codeblin.archcore.domain.ArchCoreUseCase
import com.codeblin.myapplication.ui.screens.todoList.TodoItem

class TodoUseCase(private val repository: TodoRepository) : ArchCoreUseCase<List<TodoItem>>() {
    override suspend fun execute(): List<TodoItem> {
        return repository.getTodos()
    }

    suspend fun addTodo(item: TodoItem) {
        repository.addTodo(item)
    }

    suspend fun deleteTodo(id: Int) {
        repository.deleteTodo(id)
    }

    suspend fun getTodo(id: Int): TodoItem {
        return repository.getTodo(id)
    }

}