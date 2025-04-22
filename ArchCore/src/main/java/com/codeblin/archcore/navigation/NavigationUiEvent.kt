package com.codeblin.archcore.navigation

import com.codeblin.archcore.viewmodel.UiEvent

sealed class NavigationUiEvent : UiEvent {
    data class NavigateTo(val destination: NavigationDestination) : NavigationUiEvent()
    object NavigateUp : NavigationUiEvent()
}