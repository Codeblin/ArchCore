package com.codeblin.archcore.navigation

sealed class NavigationCommand {
    data class To(val destination: NavigationDestination, val args: Map<String, String> = emptyMap()) : NavigationCommand()
    object Back : NavigationCommand()
}