package com.codeblin.myapplication.data

import com.codeblin.archcore.domain.ArchCoreDataSource
import com.codeblin.myapplication.ui.screens.todoList.TodoItem

interface TodoRemoteDataSource: ArchCoreDataSource<TodoItem>

class TodoRemoteDataSourceImpl: TodoRemoteDataSource {
    private var todos = listOf<TodoItem>()

    override suspend fun getAll(): List<TodoItem> {
        todos = listOf(
            TodoItem(1, "Task 1 from remote server", false),
            TodoItem(2, "Task 2 from remote server", false),
            TodoItem(3, "Task 3 from remote server", false),
        )
        return todos
    }

    override suspend fun getById(id: Int): TodoItem? {
        return todos.first { it.id == id }
    }

    override suspend fun save(item: TodoItem): Boolean {
        todos + item
        return true
    }

    override suspend fun delete(id: Int): Boolean {
        todos.dropWhile { it.id == id }
        return true
    }
}