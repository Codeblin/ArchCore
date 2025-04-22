package com.codeblin.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codeblin.archcore.navigation.ArchCoreNavHost
import com.codeblin.myapplication.ui.screens.todoDetails.TodoDetailsScreen
import com.codeblin.myapplication.ui.screens.todoDetails.TodoDetailsViewModel
import com.codeblin.myapplication.ui.screens.todoList.TodoScreen
import com.codeblin.myapplication.ui.screens.todoList.TodoViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavRouter() {
    val navController = rememberNavController()

    ArchCoreNavHost(
        navController = navController,
        startDestination = AppDestination.TodoList.route
    ) {
        composable(AppDestination.TodoList.route) {
            TodoScreen(viewModel = getViewModel<TodoViewModel>())
        }

        composable(
            route = AppDestination.TodoDetails.route,
            arguments = AppDestination.TodoDetails.arguments
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            val detailsViewModel =
                getViewModel<TodoDetailsViewModel>(parameters = { parametersOf(id) })
            TodoDetailsScreen(viewModel = detailsViewModel)
        }
    }
}