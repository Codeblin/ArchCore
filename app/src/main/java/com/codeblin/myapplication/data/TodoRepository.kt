package com.codeblin.myapplication.data

import android.content.Context
import com.codeblin.archcore.data.ArchCoreRepository
import com.codeblin.myapplication.ui.screens.todoList.TodoItem

class TodoRepository(
    private val remoteDataSource: TodoRemoteDataSource,
    private val localDataSource: TodoLocalDataSource,
    context: Context,
): ArchCoreRepository<List<TodoItem>>(context) {

    override suspend fun getData(): List<TodoItem> {
        return if (networkManager.isNetworkAvailable()) {
            remoteDataSource.getAll()
        } else {
            localDataSource.getAll()
        }
    }

    suspend fun addTodo(item: TodoItem) {
        if (networkManager.isNetworkAvailable()) {
            localDataSource.save(item)
        } else {
            remoteDataSource.save(item)
        }
    }

    suspend fun deleteTodo(id: Int) {
        if (networkManager.isNetworkAvailable()) {
            remoteDataSource.delete(id)
        } else {
            localDataSource.delete(id)
        }
    }

    suspend fun getTodo(id: Int): TodoItem {
        return if (networkManager.isNetworkAvailable()) {
            remoteDataSource.getById(id) ?: throw Exception("Todo not found")
        } else {
            localDataSource.getById(id) ?: throw Exception("Todo not found")
        }
    }
}