package com.codeblin.myapplication.data

import com.codeblin.myapplication.ApplicationNetworkManager
import com.codeblin.myapplication.ui.screens.todoList.TodoItem

class TodoRepository(
    private val remoteDataSource: TodoRemoteDataSource,
    private val localDataSource: TodoLocalDataSource,
    private val networkManager: ApplicationNetworkManager,
) {
    suspend fun addTodo(item: TodoItem) {
        if (networkManager.isConnected()) {
            localDataSource.save(item)
        } else {
            remoteDataSource.save(item)
        }
    }

    suspend fun deleteTodo(id: Int) {
        if (networkManager.isConnected()) {
            remoteDataSource.delete(id)
        } else {
            localDataSource.delete(id)
        }
    }

    suspend fun getTodos(): List<TodoItem> {
        return if (networkManager.isConnected()) {
            remoteDataSource.getAll()
        } else {
            localDataSource.getAll()
        }
    }

    suspend fun getTodo(id: Int): TodoItem {
        return if (networkManager.isConnected()) {
            remoteDataSource.getById(id) ?: throw Exception("Todo not found")
        } else {
            localDataSource.getById(id) ?: throw Exception("Todo not found")
        }
    }
}