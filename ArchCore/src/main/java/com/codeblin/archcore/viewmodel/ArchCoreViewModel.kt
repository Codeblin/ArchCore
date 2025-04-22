package com.codeblin.archcore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeblin.archcore.navigation.NavigationCommand
import com.codeblin.archcore.navigation.NavigationDestination
import com.codeblin.archcore.navigation.NavigationManager
import com.codeblin.archcore.navigation.NavigationUiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class ArchCoreViewModel<S : UiState, E : UiEvent, I : UiIntent>(
    initialState: S
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<S> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<E>()
    val uiEvent: SharedFlow<E> = _uiEvent.asSharedFlow()

    abstract fun processIntent(intent: I)

    protected fun updateState(newState: S) {
        _uiState.value = newState
    }

    protected fun sendEvent(event: E) {
        viewModelScope.launch { _uiEvent.emit(event) }
    }

    protected fun navigateTo(destination: NavigationDestination, vararg args: Pair<String, String>) {
        viewModelScope.launch {
            NavigationManager.emit(NavigationCommand.To(destination, mapOf(*args)))
        }
    }

    protected fun navigateBack() {
        viewModelScope.launch {
            NavigationManager.emit(NavigationCommand.Back)
        }
    }
}

