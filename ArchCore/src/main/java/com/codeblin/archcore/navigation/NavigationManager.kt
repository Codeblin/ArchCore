package com.codeblin.archcore.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object NavigationManager {
    private val mutex = Mutex()
    private val _navigationFlow = MutableSharedFlow<NavigationCommand>()
    val navigationFlow: SharedFlow<NavigationCommand> = _navigationFlow

    suspend fun emit(command: NavigationCommand) {
        mutex.withLock {
            _navigationFlow.emit(command)
        }
    }
}