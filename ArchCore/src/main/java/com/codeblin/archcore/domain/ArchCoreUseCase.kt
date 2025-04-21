package com.codeblin.archcore.domain

// Core module
abstract class ArchCoreUseCase<R> {
    abstract suspend fun execute(): R
}
