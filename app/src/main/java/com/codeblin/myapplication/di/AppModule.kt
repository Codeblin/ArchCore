package com.codeblin.myapplication.di

import com.codeblin.myapplication.data.TodoLocalDataSource
import com.codeblin.myapplication.data.TodoLocalDataSourceImpl
import com.codeblin.myapplication.data.TodoRemoteDataSource
import com.codeblin.myapplication.data.TodoRemoteDataSourceImpl
import com.codeblin.myapplication.data.TodoRepository
import com.codeblin.myapplication.ui.screens.todoDetails.TodoDetailsViewModel
import com.codeblin.myapplication.ui.screens.todoList.TodoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    // Bind implementations to an interface (if applicable)
    single<TodoRemoteDataSource>(named("remote")) { TodoRemoteDataSourceImpl() }
    single<TodoLocalDataSource>(named("local")) { TodoLocalDataSourceImpl() }

    // Repository using both local and remote sources
    single {
        TodoRepository(
            remoteDataSource = get(named("remote")),  // Explicitly fetch remote
            localDataSource = get(named("local")),    // Explicitly fetch local
            context = androidContext()
        )
    }

    // ViewModel
    viewModel { TodoViewModel(get()) }
    viewModel { TodoDetailsViewModel() }
}