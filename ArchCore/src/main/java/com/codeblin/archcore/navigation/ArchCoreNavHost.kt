package com.codeblin.archcore.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun ArchCoreNavHost(
    navController: NavHostController,
    startDestination: String,
    buildGraph: NavGraphBuilder.() -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        buildGraph()
    }

    LaunchedEffect(Unit) {
        NavigationManager.navigationFlow.collect { command ->
            when (command) {
                is NavigationCommand.To -> navController.navigate(
                    command.destination.createRoute(
                        command.args
                    )
                )

                is NavigationCommand.Back -> navController.popBackStack()
            }
        }
    }
}