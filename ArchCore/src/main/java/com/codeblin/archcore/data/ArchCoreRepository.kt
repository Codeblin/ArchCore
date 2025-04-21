package com.codeblin.archcore.data

interface ArchCoreRepository<T> {
    suspend fun getData(): T
}