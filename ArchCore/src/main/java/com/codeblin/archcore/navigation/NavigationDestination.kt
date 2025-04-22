package com.codeblin.archcore.navigation

import androidx.navigation.NamedNavArgument

interface NavigationDestination {
    val route: String
    val arguments: List<NamedNavArgument>

    fun createRoute(args: Map<String, String> = emptyMap()): String {
        var builtRoute = route
        args.forEach { (key, value) ->
            builtRoute = builtRoute.replace("{$key}", value)
        }
        return builtRoute
    }
}