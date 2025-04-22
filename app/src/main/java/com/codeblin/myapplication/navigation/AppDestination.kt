package com.codeblin.myapplication.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.codeblin.archcore.navigation.NavigationDestination

sealed class AppDestination(
    override val route: String,
    override val arguments: List<NamedNavArgument> = emptyList()
) : NavigationDestination {

    data object TodoList : AppDestination("home")

    data object TodoDetails : AppDestination(
        route = "id/{id}",
        arguments = listOf(navArgument("id") { type = NavType.StringType })
    )
}